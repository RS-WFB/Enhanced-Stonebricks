package net.rswfb.enhancedstonebricks.network.handler;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.TickEvent;
import net.rswfb.enhancedstonebricks.EnhancedStonebricks;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = EnhancedStonebricks.MODID)
public class DelayedTaskManager {
    private static final Map<Runnable, Integer> tasks = new HashMap<>();

    // 注册延迟任务
    public static void scheduleTask(Runnable task, int delayTicks) {
        tasks.put(task, delayTicks);
    }

    // 处理 Tick 事件
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        tasks.entrySet().removeIf(entry -> {
            int remaining = entry.getValue() - 1;
            if (remaining <= 0) {
                entry.getKey().run();
                return true; // 移除任务
            } else {
                entry.setValue(remaining);
                return false;
            }
        });
    }
}
