/*
 * Copyright (C) 2014 .com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.administrator.dbtools.base.util;

import android.text.TextUtils;

import com.example.administrator.dbtools.base.App;


public abstract class LogUtils {
    public static String customTagPrefix = "";

//    public static boolean allowD = !AppBase.inProduceMode;
//
//    public static boolean allowE = !AppBase.inProduceMode;
//
//    public static boolean allowI = !AppBase.inProduceMode;
//
//    public static boolean allowV = !AppBase.inProduceMode;
//
//    public static boolean allowW = !AppBase.inProduceMode;
//
//    public static boolean allowWtf = !AppBase.inProduceMode;

    public static CustomLogger customLogger;

    private LogUtils() {
    }

    private static String generateTag(StackTraceElement caller) {
        String tag = "%s.%s(L:%d)";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
        tag = TextUtils.isEmpty(customTagPrefix) ? tag : customTagPrefix + ":" + tag;
        return tag;
    }

    public static void debug(String content) {
        if (App.getApp().inProduceMode || TextUtils.isEmpty(content)){
            return;
        }
//        StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
//        String tag = generateTag(caller);
//
//        if (customLogger != null) {
//            customLogger.debug(tag, content);
//        } else {
//            Log.d(tag, content);
//        }
    }
    
    /**
     * 把日志保存到文件中
     * @param content
     * @param fileName
     */
    public static synchronized void debug(String content, String fileName) {
        if (App.getApp().inProduceMode || TextUtils.isEmpty(content)){
            return;
        }
//        StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
//        String tag = generateTag(caller);
//
//        if (customLogger != null) {
//            customLogger.debug(tag, content);
//        } else {
//            Log.d(tag, content);
//        }
    }

    public static void debug(String content, Throwable tr) {
        if (App.getApp().inProduceMode || TextUtils.isEmpty(content)){
            return;
        }
//        StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
//        String tag = generateTag(caller);
//
//        if (customLogger != null) {
//            customLogger.debug(tag, content, tr);
//        } else {
//            Log.d(tag, content, tr);
//        }
    }

    public static void error(String content) {
        if (App.getApp().inProduceMode || TextUtils.isEmpty(content)){
            return;
        }
//        StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
//        String tag = generateTag(caller);

        if (customLogger != null) {
//            customLogger.error(tag, content);
        } else {
//            Log.e(tag, content);
        }
    }

    public static void error(String content, Throwable tr) {
        if (App.getApp().inProduceMode || TextUtils.isEmpty(content)){
            return;
        }
//        StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
//        String tag = generateTag(caller);
//
//        if (customLogger != null) {
//            customLogger.error(tag, content, tr);
//        } else {
//            Log.e(tag, content, tr);
//        }
    }

    public static void info(String content) {
        if (App.getApp().inProduceMode || TextUtils.isEmpty(content)){
            return;
        }
//        StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
//        String tag = generateTag(caller);
//
//        if (customLogger != null) {
//            customLogger.info(tag, content);
//        } else {
//            Log.i(tag, content);
//        }
    }

    public static void info(String content, Throwable tr) {
        if (App.getApp().inProduceMode || TextUtils.isEmpty(content)){
            return;
        }
//        StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
//        String tag = generateTag(caller);
//
//        if (customLogger != null) {
//            customLogger.info(tag, content, tr);
//        } else {
//            Log.i(tag, content, tr);
//        }
    }

    public static void verbose(String content) {
        if (App.getApp().inProduceMode || TextUtils.isEmpty(content)){
            return;
        }
//        StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
//        String tag = generateTag(caller);
//
//        if (customLogger != null) {
//            customLogger.verbose(tag, content);
//        } else {
//            Log.v(tag, content);
//        }
    }

    public static void verbose(String content, Throwable tr) {
        if (App.getApp().inProduceMode || TextUtils.isEmpty(content)){
            return;
        }
//        StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
//        String tag = generateTag(caller);
//
//        if (customLogger != null) {
//            customLogger.verbose(tag, content, tr);
//        } else {
//            Log.v(tag, content, tr);
//        }
    }

    public static void warn(String content) {
        if (App.getApp().inProduceMode || TextUtils.isEmpty(content)){
            return;
        }
//        StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
//        String tag = generateTag(caller);
//
//        if (customLogger != null) {
//            customLogger.warn(tag, content);
//        } else {
//            Log.w(tag, content);
//        }
    }

    public static void warn(String content, Throwable tr) {
        if (App.getApp().inProduceMode || TextUtils.isEmpty(content)){
            return;
        }
//        StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
//        String tag = generateTag(caller);
//
//        if (customLogger != null) {
//            customLogger.warn(tag, content, tr);
//        } else {
//            Log.w(tag, content, tr);
//        }
    }

    public static void warn(Throwable tr) {
          if (App.getApp().inProduceMode) {
              return;
          }
//        StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
//        String tag = generateTag(caller);
//
//        if (customLogger != null) {
//            customLogger.warn(tag, tr);
//        } else {
//            Log.w(tag, tr);
//        }
    }

    public static void wtf(String content) {
        if (App.getApp().inProduceMode || TextUtils.isEmpty(content)){
            return;
        }
//        StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
//        String tag = generateTag(caller);
//
//        if (customLogger != null) {
//            customLogger.wtf(tag, content);
//        } else {
//            Log.wtf(tag, content);
//        }
    }

    public static void wtf(String content, Throwable tr) {
        if (App.getApp().inProduceMode || TextUtils.isEmpty(content)){
            return;
        }
//        StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
//        String tag = generateTag(caller);
//
//        if (customLogger != null) {
//            customLogger.wtf(tag, content, tr);
//        } else {
//            Log.wtf(tag, content, tr);
//        }
    }

    public static void wtf(Throwable tr) {
        if (App.getApp().inProduceMode){
            return;
        }
//        StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
//        String tag = generateTag(caller);
//
//        if (customLogger != null) {
//            customLogger.wtf(tag, tr);
//        } else {
//            Log.wtf(tag, tr);
//        }
    }
    
    

    public interface CustomLogger {
        void debug(String tag, String content);

        void debug(String tag, String content, Throwable tr);

        void error(String tag, String content);

        void error(String tag, String content, Throwable tr);

        void info(String tag, String content);

        void info(String tag, String content, Throwable tr);

        void verbose(String tag, String content);

        void verbose(String tag, String content, Throwable tr);

        void warn(String tag, String content);

        void warn(String tag, String content, Throwable tr);

        void warn(String tag, Throwable tr);

        void wtf(String tag, String content);

        void wtf(String tag, String content, Throwable tr);

        void wtf(String tag, Throwable tr);

    }

}
