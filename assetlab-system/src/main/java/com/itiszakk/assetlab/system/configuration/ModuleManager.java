package com.itiszakk.assetlab.system.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.itiszakk.assetlab.system.service.PropertyService;
import com.itiszakk.assetlab.system.service.TextService;
import com.itiszakk.assetlab.system.service.impl.PropertyServiceImpl;
import com.itiszakk.assetlab.system.service.impl.TextServiceImpl;

public class ModuleManager {

    private final Map<String, Module> modules = new HashMap<>();

    private final ApplicationContext context;

    private final TextService textService;

    private final PropertyService propertyService;

    public ModuleManager(ApplicationContext context) {
        this.context = context;
        this.textService = new TextServiceImpl();
        this.propertyService = new PropertyServiceImpl();

        context.register(TextService.class, textService);
        context.register(PropertyService.class, this.propertyService);
    }

    public void register(Module module) {
        modules.put(module.getId(), module);
    }

    public void start() {

        List<Module> ordered = resolveOrder();

        for (Module module : ordered) {

            textService.register(module.getTextBundle(), module.getClass().getClassLoader());
            propertyService.register(propertyService.getProperties());

            module.init(context);
            module.start();
        }
    }

    private List<Module> resolveOrder() {

        Map<String, Integer> incoming = new HashMap<>();
        Map<String, List<String>> graph = new HashMap<>();

        for (Module module : modules.values()) {

            String moduleId = module.getId();
            incoming.putIfAbsent(moduleId, 0);

            for (String dependency : module.getDependencies()) {

                graph.computeIfAbsent(dependency, k -> new ArrayList<>())
                        .add(moduleId);

                incoming.put(moduleId, incoming.getOrDefault(moduleId, 0) + 1);
            }
        }

        Queue<String> queue = new LinkedList<>();
        for (var entry : incoming.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
            }
        }

        List<Module> result = new ArrayList<>();
        while (!queue.isEmpty()) {

            String moduleId = queue.poll();
            result.add(modules.get(moduleId));

            for (String dependency : graph.getOrDefault(moduleId, List.of())) {
                int degree = incoming.merge(dependency, -1, Integer::sum);
                if (degree == 0) {
                    queue.add(dependency);
                }
            }
        }

        if (result.size() != modules.size()) {
            throw new IllegalStateException("Cycle detected in module dependencies");
        }

        return result;
    }
}
