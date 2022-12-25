package _03经典面试题目.图的优先遍历;

import java.io.File;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 统计一个文件夹里的文件数目（包括子文件夹），文件夹不算文件
 * Created by huangjunyi on 2022/12/18.
 */
public class _02CountFiles {

    // 注意这个函数也会统计隐藏文件
    public static int getFileNumber(String folderPath) {
        File file = new File(folderPath);
        if (!file.exists()) return 0;
        if (file.isFile()) return 1;
        LinkedList<File> stack = new LinkedList<>();
        stack.push(file);
        int res = 0;
        while (!stack.isEmpty()) {
            File curFile = stack.pop();
            if (curFile.isFile()) res++;
            if (curFile.isDirectory()) {
                for (File subFile : curFile.listFiles()) {
                    if (subFile.isFile()) {
                        res++;
                    }
                    if (subFile.isDirectory()) {
                        stack.push(subFile);
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        // 你可以自己更改目录
        String path = "C:\\Users\\lenovo\\Desktop\\";
        System.out.println(getFileNumber(path));
    }

}
