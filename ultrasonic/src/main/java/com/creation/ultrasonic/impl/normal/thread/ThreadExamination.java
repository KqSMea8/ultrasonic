package com.creation.ultrasonic.impl.normal.thread;

import android.util.Log;

import com.creation.ultrasonic.DoctorSingleton;
import com.creation.ultrasonic.IProbe;
import com.creation.ultrasonic.impl.Examination;
import com.creation.ultrasonic.impl.HookProbe;
import com.creation.ultrasonic.impl.PrintUtils;
import com.creation.ultrasonic.impl.hook.MethodSign;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadExamination extends Examination {
    private static final String TAG = "ThreadExamination";

    @Override
    protected ArrayList<IProbe> initProbeList() {
        ArrayList<IProbe> arrayList = new ArrayList<>();


        arrayList.add(new HookThread_1());
//        arrayList.add(new HookThread_2());
        arrayList.add(new HookThread_3());
        arrayList.add(new HookThread_4());
        arrayList.add(new HookThread_5());
        arrayList.add(new HookThread_6());
        arrayList.add(new HookThread_7());
        arrayList.add(new HookThread_8());
        arrayList.add(new HookThread_9());

//        arrayList.add(new HookThreadSetName());
//        arrayList.add(new HookThreadStart());
//
//        arrayList.add(new HookThreadPool());

        return arrayList;
    }

    private static void printCreateThread(Thread thread) {
        String clazzName = PrintUtils.getStackClass(ThreadExamination.class.getName(), Thread.class.getName(), DoctorSingleton.instance().getPrintFilter());
        Log.e(TAG, clazzName == null ? "native stack!!!" : clazzName);
    }

    public static class HookThread_1 extends HookProbe{
        @Override
        protected MethodSign getMethodSign() {
            return MethodSign.createVoid(
                    Thread.class,
                    "<init>",
                    null
            );
        }

        public static Thread hook(Object object) {
            Thread thread = backup(object);
            printCreateThread(thread);
            return thread;
        }

        public static Thread backup(Object object) {
            return null;
        }
    }

    public static class HookThread_2 extends HookProbe{
        private static final String TAG = "HookThread_2";
        @Override
        protected MethodSign getMethodSign() {
            return MethodSign.create(
                    Thread.class,
                    "<init>",
                    new Class[] {
                            String.class
                    },
                    Thread.class
            );
        }

        public static Thread hook(Object object,String threadName) {
            Thread thread = backup(object, threadName);
            printCreateThread(thread);
            return thread;
        }

        public static Thread backup(Object object,String threadName) {
            return null;
        }
    }

    public static class HookThread_3 extends HookProbe{
        @Override
        protected MethodSign getMethodSign() {
            return MethodSign.createVoid(
                    Thread.class,
                    "<init>",
                    new Class[] {
                            Runnable.class
                    }
            );
        }

        public static Thread hook(Object object,Runnable runnable) {
            Thread thread = backup(object, runnable);
            printCreateThread(thread);
            return thread;
        }

        public static Thread backup(Object object,Runnable runnable) {
            return null;
        }
    }

    public static class HookThread_4 extends HookProbe{
        @Override
        protected MethodSign getMethodSign() {
            return MethodSign.createVoid(
                    Thread.class,
                    "<init>",
                    new Class[] {
                            Runnable.class,
                            String.class
                    }
            );
        }

        public static Thread hook(Object object,Runnable runnable,String threadName) {
            Thread thread = backup(object, runnable, threadName);
            printCreateThread(thread);
            return thread;
        }

        public static Thread backup(Object object,Runnable runnable,String threadName) {
            return null;
        }
    }

    public static class HookThread_5 extends HookProbe{
        @Override
        protected MethodSign getMethodSign() {
            return MethodSign.createVoid(
                    Thread.class,
                    "<init>",
                    new Class[] {
                            ThreadGroup.class,
                            String.class
                    }
            );
        }

        public static Thread hook(Object object,ThreadGroup group, String threadName) {
            Thread thread = backup(object, group, threadName);
            printCreateThread(thread);
            return thread;
        }

        public static Thread backup(Object object,ThreadGroup group, String threadName) {
            return null;
        }

    }

    public static class HookThread_6 extends HookProbe {
        @Override
        protected MethodSign getMethodSign() {
            return MethodSign.createVoid(
                    Thread.class,
                    "<init>",
                    new Class[] {
                            ThreadGroup.class,
                            Runnable.class
                    }
            );
        }

        public static Thread hook(Object object,ThreadGroup group, Runnable target) {
            Thread thread = backup(object, group, target);
            printCreateThread(thread);
            return thread;
        }

        public static Thread backup(Object object,ThreadGroup group, Runnable target) {
            return null;
        }
    }

    public static class HookThread_7 extends HookProbe{
        @Override
        protected MethodSign getMethodSign() {
            return MethodSign.createVoid(
                    Thread.class,
                    "<init>",
                    new Class[] {
                            ThreadGroup.class,
                            Runnable.class,
                            String.class
                    }
            );
        }

        public static Thread hook(Object object,ThreadGroup group, Runnable target,String name) {
            Thread thread = backup(object, group, target, name);
            printCreateThread(thread);
            return thread;
        }

        public static Thread backup(Object object,ThreadGroup group, Runnable target,String name) {
            return null;
        }
    }

    public static class HookThread_8 extends HookProbe{
        @Override
        protected MethodSign getMethodSign() {
            return MethodSign.createVoid(
                    Thread.class,
                    "<init>",
                    new Class[] {
                            ThreadGroup.class,
                            Runnable.class,
                            String.class,
                            long.class
                    }
            );
        }

        public static Thread hook(Object object,ThreadGroup threadGroup,Runnable runnable,String threadName,long stackSize) {
            Thread thread = backup(object, threadGroup, runnable, threadName, stackSize);
            printCreateThread(thread);
            return thread;
        }

        public static Thread backup(Object object,ThreadGroup threadGroup,Runnable runnable,String threadName,long stackSize) {
            return null;
        }
    }

    public static class HookThread_9 extends HookProbe{
        @Override
        protected MethodSign getMethodSign() {
            return MethodSign.createVoid(
                    Thread.class,
                    "<init>",
                    new Class[] {
                            ThreadGroup.class,
                            String.class,
                            int.class,
                            boolean.class
                    }
            );
        }

        public static Thread hook(Object object,ThreadGroup group, String name, int priority, boolean daemon) {
            Thread thread = backup(object, group, name, priority, daemon);
            printCreateThread(thread);
            return thread;
        }

        public static Thread backup(Object object,ThreadGroup group, String name, int priority, boolean daemon) {
            return null;
        }
    }

    public static class HookThreadSetName extends HookProbe {
        @Override
        protected MethodSign getMethodSign() {
            return MethodSign.createVoid(
                    Thread.class,
                    "setName",
                    new Class[]{
                            String.class,
                    }
            );
        }

        public static void hook(Object object, String name) {
            backup(object,name);
        }

        public static void backup(Object object, String name) {}
    }

    public static class HookThreadStart extends HookProbe{
        @Override
        protected MethodSign getMethodSign() {
            return MethodSign.createVoid(
                    Thread.class,
                    "start",
                    new Class[]{});
        }

        public static void hook(Object object) {
            backup(object);
        }

        public static void backup(Object object) {

        }
    }

    public static class HookThreadPool extends HookProbe {
        @Override
        protected MethodSign getMethodSign() {
            return MethodSign.createVoid(
                    ThreadPoolExecutor.class,
                    "<init>",
                    new Class<?>[]{
                            int.class,
                            int.class,
                            long.class,
                            TimeUnit.class,
                            BlockingQueue.class,
                            ThreadFactory.class
                    }
            );
        }

        public static ThreadPoolExecutor hook(Object object, int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                              BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
            return backup(object, corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        }

        public static ThreadPoolExecutor backup(Object object, int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                                BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
            return null;
        }
    }
}
