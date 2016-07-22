package com.lenovo.compass.compass.log;

import android.util.Log;

import com.ksyun.android.ddlive.KsyunApplication;
import com.ksyun.android.ddlive.utils.FileUtil;
import com.ksyun.android.ddlive.utils.MediaUtil;
import com.ksyun.android.ddlive.utils.Utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by hansentian on 5/18/14.
 */
public class LogUtil {
    public final static String TAG_PREFEX = "KSY_LOG_";
    public final static String TAG = "LogUtil";
    public final static int LOG_VERBOSE = 0;
    public final static int LOG_DEBUG = 1;
    public final static int LOG_INFO = 2;
    public final static int LOG_WARN = 3;
    public final static int LOG_ERROR = 4;
    public final static int LOG_SILENT = 5;

    private final static int LOG_MAX_MEM = 16384; // 16k
    private final static int LOG_MAX_LEN = 2048; // 2k
    private final static long LOG_FILE_LEN = 4194304; // 4MB

    private static StringBuilder mStringBuilder;

    private static boolean mLocalLog = false;
    private static boolean mPrintLog = false;
    private static int mLogLevel = LOG_SILENT;

    public static void init() {
        if (KsyunApplication.DEBUG) {
            mPrintLog = true;
            mLocalLog = true;
            mLogLevel = LOG_VERBOSE;
            StringBuffer log = new StringBuffer();
            log.append("\n");
            log.append("                   _ooOoo_\n");
            log.append("                  o8888888o\n");
            log.append("                  88\" . \"88\n");
            log.append("                  (| -_- |)\n");
            log.append("                  O\\  =  /O\n");
            log.append("               ____/`---'\\____\n");
            log.append("             .'  \\\\|     |//  `.\n");
            log.append("            /  \\\\|||  :  |||//  \\ \n");
            log.append("           /  _||||| -:- |||||-  \\ \n");
            log.append("           |   | \\\\\\  -  /// |   |\n");
            log.append("           | \\_|  ''\\---/''  |   |\n");
            log.append("           \\  .-\\__  `-`  ___/-. /\n");
            log.append("         ___`. .'  /--.--\\  `. . __\n");
            log.append("      .\"\" '<  `.___\\_<|>_/___.'  >'\"\".\n");
            log.append("     | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |\n");
            log.append("     \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /\n");
            log.append("======`-.____`-.___\\_____/___.-`____.-'======\n");
            log.append("                   `=---='\n");
            log.append("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
            log.append("\t\t佛祖保佑       永无BUG\n");
            Log.e(TAG, log.toString());
        } else {
            mPrintLog = false;
            mLocalLog = true;
            mLogLevel = LOG_ERROR;
        }
        mStringBuilder = new StringBuilder();
    }

    public static void setLogLevel(int logLevel) {
        mLogLevel = logLevel;
    }

    public static int getLogLevel() {
        return mLogLevel;
    }

    public static void setLocalLog(boolean local) {
        mLocalLog = local;
    }

    public static void setPrintLog(boolean print) {
        mPrintLog = print;
    }

    private static void cutLog(int level, String tag, String msg) {
        if (msg == null || tag == null) {
            return;
        }
        while (msg.length() > LOG_MAX_LEN) {
            String piece = msg.substring(0, LOG_MAX_LEN);
            printLog(level, tag, piece);
            msg = msg.substring(LOG_MAX_LEN);
        }
        printLog(level,  tag, msg);
    }

    private static void printLog(int level, String tag, String log) {
        if (level == LOG_VERBOSE) {
            Log.v(tag, log);
        } else if (level == LOG_DEBUG) {
            Log.d(tag, log);
        } else if (level == LOG_INFO) {
            Log.i(tag, log);
        } else if (level == LOG_WARN) {
            Log.w(tag, log);
        } else if (level == LOG_ERROR) {
            Log.e(tag, log);
        }
    }


    public static void v(String tag, String msg) {
        if (LOG_VERBOSE < mLogLevel) {
            return;
        }

        if (mPrintLog) {
            cutLog(LOG_VERBOSE, TAG_PREFEX + tag, msg);
        }

        if (mLocalLog) {
            appendInfo("V", TAG_PREFEX + tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (LOG_DEBUG < mLogLevel) {
            return;
        }

        if (mPrintLog) {
            cutLog(LOG_DEBUG, TAG_PREFEX + tag, msg);
        }

        if (mLocalLog) {
            appendInfo("D", TAG_PREFEX + tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (LOG_INFO < mLogLevel) {
            return;
        }

        if (mPrintLog) {
            cutLog(LOG_INFO, TAG_PREFEX + tag, msg);
        }

        if (mLocalLog) {
            appendInfo("I", TAG_PREFEX + tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (LOG_WARN < mLogLevel) {
            return;
        }

        if (mPrintLog) {
            cutLog(LOG_WARN, TAG_PREFEX + tag, msg);
        }

        if (mLocalLog) {
            appendInfo("W", TAG_PREFEX + tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (LOG_ERROR < mLogLevel) {
            return;
        }

        if (mPrintLog) {
            cutLog(LOG_ERROR, TAG_PREFEX + tag, msg);
        }

        if (mLocalLog) {
            appendInfo("E", TAG_PREFEX + tag, msg);
        }
    }

    private static void appendInfo(String logLevel, String tag, String msg) {
        // print date information in log
        String info = logLevel + " " + tag + " " + msg + "\n";

        synchronized (mStringBuilder) {
            mStringBuilder.append(info);
            int length = mStringBuilder.length();
            // clear contents
            if (length > LOG_MAX_MEM) {
                flushLog();
            }
        }
    }

    public static void onDestroy() {
        flushLog();
    }

    public static void flushLog() {
        LogWriter writer = new LogWriter();
        Thread writerThread = new Thread(writer);
        writerThread.start();
    }

    private static class LogWriter implements Runnable {
        public void run() {
            writeLog2File();
        }
    }

    private static void writeLog2File() {
        String log = null;
        synchronized (mStringBuilder) {
            log = mStringBuilder.toString();
            int length = mStringBuilder.length();
            mStringBuilder.delete(0, length);
        }

        String logDir = MediaUtil.getLogDir();
        if (Utils.isEmpty(logDir)) {
            return;
        }

        String logPath = logDir + File.separator + "ddlive.log";
        File logFile = new File(logPath);
        if (!logFile.exists()) {
            FileUtil.create(logPath);
        }
        if (logFile.length() >= LOG_FILE_LEN) {
            cutLogFile(logFile);
        }

        try {
            FileOutputStream fos = new FileOutputStream(logFile, true);
            fos.write(log.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void cutLogFile(File logFile) {
        BufferedOutputStream bos = null;
        RandomAccessFile raf = null;
        try {
            String tempLog = MediaUtil.getLogDir();
            if (Utils.isEmpty(tempLog)) {
                return;
            }
            tempLog = tempLog + File.separator + "temp.log";
            FileUtil.delete(tempLog);
            File tempFile = FileUtil.create(tempLog);
            if (tempFile == null) return;
            // TODO need find out the reason of tempFile == null
            bos = new BufferedOutputStream(new FileOutputStream(tempFile));
            raf = new RandomAccessFile(logFile, "r");
            raf.seek(LOG_FILE_LEN / 2);
            int readLen = 0;
            byte[] readBuff = new byte[1024];
            while ((readLen = raf.read(readBuff)) != -1) {
                bos.write(readBuff, 0, readLen);
            }

            logFile.delete();
            tempFile.renameTo(logFile);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
                if (raf != null) {
                    raf.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
