package com.creation.ultrasonic.impl.normal.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;

import com.creation.ultrasonic.DoctorSingleton;
import com.creation.ultrasonic.IProbe;
import com.creation.ultrasonic.impl.Examination;
import com.creation.ultrasonic.impl.HookProbe;
import com.creation.ultrasonic.impl.PrintUtils;
import com.creation.ultrasonic.impl.hook.MethodSign;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class BitmapExamination extends Examination {
    private static final String TAG = "BitmapExamination";

    private static final DecimalFormat sFormat = new DecimalFormat("#,###");
    private static long sBitmapTotalSize = 0;

    @Override
    protected ArrayList<IProbe> initProbeList() {
        ArrayList<IProbe> arrayList = new ArrayList<>();

        arrayList.add(new CreateBitmap_1());
        arrayList.add(new CreateBitmap_2());
        arrayList.add(new CreateBitmap_3());
        arrayList.add(new CreateBitmap_4());
        arrayList.add(new DecodeStream());
        arrayList.add(new DecodeFileDescriptor());

        return arrayList;
    }

    private static void printCreateBitmap(Bitmap bitmap, BitmapFactory.Options opts, boolean decode) {
        printCreateBitmap(bitmap, opts, decode, null);
    }

    private static void printCreateBitmap(Bitmap bitmap, BitmapFactory.Options opts, boolean decode, String path) {
        if (bitmap == null) {
            return;
        }
        if (opts != null && opts.inBitmap != null) {
            return;
        }

        String className = decode ? BitmapFactory.class.getName() : Bitmap.class.getName();
        String clazzName = PrintUtils.getStackClass(BitmapExamination.class.getName(), className, DoctorSingleton.instance().getPrintFilter());

        int byteCount = bitmap.getByteCount();
        synchronized (BitmapExamination.class) {
            sBitmapTotalSize += byteCount;
        }
        String text = "size:" + sFormat.format(byteCount) +
                " width:" + bitmap.getWidth() +
                " height:" + bitmap.getHeight() +
                " total:" + sFormat.format(sBitmapTotalSize) +
                " " + clazzName;
        String head = decode ? "decode " : "create ";
        text = head + text;
        if (byteCount < 180 * 180 * 4) {
            Log.i(TAG, text);
        } else {
            Log.e(TAG, text);
        }
    }

    public static class CreateBitmap_1 extends HookProbe {
        @Override
        protected MethodSign getMethodSign() {
            return MethodSign.create(
                    Bitmap.class,
                    "createBitmap",
                    new Class<?>[] {
                            int.class,
                            int.class,
                            Bitmap.Config.class
                    },
                    Bitmap.class
            );
        }

        public static Bitmap hook(int width, int height, Bitmap.Config config) {
            Bitmap bitmap = backup(width, height, config);
            printCreateBitmap(bitmap, null, false);
            return bitmap;
        }

        public static Bitmap backup(int width, int height, Bitmap.Config config) {
            return null;
        }
    }

    public static class CreateBitmap_2 extends HookProbe {
        @Override
        protected MethodSign getMethodSign() {
            return MethodSign.create(
                    Bitmap.class,
                    "createBitmap",
                    new Class<?>[] {
                            Bitmap.class,
                            int.class,
                            int.class,
                            int.class,
                            int.class,
                            Matrix.class,
                            boolean.class
                    },
                    Bitmap.class
            );
        }

        public static Bitmap hook(Bitmap source, int x, int y, int width, int height,
                           Matrix m, boolean filter) {
            Bitmap bitmap = backup(source, x, y, width, height, m, filter);
            printCreateBitmap(bitmap, null, false);
            return bitmap;
        }

        public static Bitmap backup(Bitmap source, int x, int y, int width, int height,
                             Matrix m, boolean filter) {
            return null;
        }
    }

    public static class CreateBitmap_3 extends HookProbe {
        @Override
        protected MethodSign getMethodSign() {
            return MethodSign.create(
                    Bitmap.class,
                    "createBitmap",
                    new Class<?>[] {
                            DisplayMetrics.class,
                            int.class,
                            int.class,
                            Bitmap.Config.class
                    },
                    Bitmap.class
            );
        }

        public static Bitmap hook(DisplayMetrics display, int width, int height, Bitmap.Config config) {
            Bitmap bitmap = backup(display, width, height, config);
            printCreateBitmap(bitmap, null, false);
            return bitmap;
        }

        public static Bitmap backup(DisplayMetrics display, int width, int height, Bitmap.Config config) {
            return null;
        }
    }

    public static class CreateBitmap_4 extends HookProbe {
        @Override
        protected MethodSign getMethodSign() {
            return MethodSign.create(
                    Bitmap.class,
                    "createBitmap",
                    new Class<?>[] {
                            DisplayMetrics.class,
                            int[].class,
                            int.class,
                            int.class,
                            int.class,
                            int.class,
                            Bitmap.Config.class
                    },
                    Bitmap.class
            );
        }

        public static Bitmap hook(DisplayMetrics display, int colors[],
                           int offset, int stride, int width, int height, Bitmap.Config config) {
            Bitmap bitmap = backup(display, colors, offset, stride, width, height, config);
            printCreateBitmap(bitmap, null, false);
            return bitmap;
        }

        public static Bitmap backup(DisplayMetrics display, int colors[],
                             int offset, int stride, int width, int height, Bitmap.Config config) {
            return null;
        }
    }

    public static class DecodeStream extends HookProbe {
        @Override
        protected MethodSign getMethodSign() {
            return MethodSign.create(
                    BitmapFactory.class,
                    "decodeStream",
                    new Class<?>[] {
                            InputStream.class,
                            Rect.class,
                            BitmapFactory.Options.class
                    },
                    Bitmap.class
            );
        }

        public static Bitmap hook(InputStream is, Rect outPadding, BitmapFactory.Options opts) {
            Bitmap bitmap = backup(is, outPadding, opts);
            printCreateBitmap(bitmap, opts, true);
            return bitmap;
        }

        public static Bitmap backup(InputStream is, Rect outPadding, BitmapFactory.Options opts) {
            return null;
        }
    }

    public static class DecodeFileDescriptor extends HookProbe {
        @Override
        protected MethodSign getMethodSign() {
            return MethodSign.create(
                    BitmapFactory.class,
                    "decodeFileDescriptor",
                    new Class<?>[] {
                            FileDescriptor.class,
                            Rect.class,
                            BitmapFactory.Options.class
                    },
                    Bitmap.class
            );
        }

        public static Bitmap hook(FileDescriptor fd, Rect outPadding, BitmapFactory.Options opts) {
            Bitmap bitmap = backup(fd, outPadding, opts);
            printCreateBitmap(bitmap, opts, true);
            return bitmap;
        }

        public static Bitmap backup(FileDescriptor fd, Rect outPadding, BitmapFactory.Options opts) {
            return null;
        }
    }
}
