package com.jalynn.server;

import org.junit.jupiter.api.Test;
import org.python.core.*;
import org.python.util.PythonInterpreter;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Base64;

@SpringBootTest
class AiDiagnosisServerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void test(){
        PySystemState sys = Py.getSystemState();
        System.out.println(sys.path.toString());
        PythonInterpreter interpreter = new PythonInterpreter();

        interpreter.exec("import sys");
        interpreter.exec("sys.path.append('E:\\\\Anaconda3\\\\envs\\\\Pytorch_envs\\\\lib\\\\site-packages')");
        //interpreter.exec("sys.path.append('E:\\\\Anaconda3\\\\envs\\\\Pytorch_envs\\\\Lib\\\\site-packages\\\\torchvision')");
        //interpreter.exec("sys.path.append('E:\\\\Anaconda3\\\\envs\\\\Pytorch_envs\\\\python38.zip')");
        //interpreter.exec("sys.path.append('E:\\\\Anaconda3\\\\envs\\\\Pytorch_envs')");
        interpreter.exec("sys.path.append('E:\\\\Anaconda3\\\\envs\\\\Pytorch_envs\\\\DLLs')");
        interpreter.exec("sys.path.append('E:\\\\Anaconda3\\\\envs\\\\Pytorch_envs\\\\lib\\\\site-packages\\\\Pythonwin')");
        //interpreter.exec("sys.path.append('D:\\\\Program Files\\\\Apache\\\\maven-repository\\\\org\\\\python\\\\jython-standalone\\\\2.7.2\\\\Lib')");
        //interpreter.exec("sys.path.append('D:\\\\Program Files\\\\Apache\\\\maven-repository\\\\org\\\\python\\\\jython-standalone\\\\2.7.2\\\\jython-standalone-2.7.2.jar/Lib')");
        interpreter.exec("sys.path.append('E:\\\\GraduationProject\\\\Dataset\\\\COVID-19_Radiography_Dataset')");

        interpreter.execfile("E:\\GraduationProject\\Dataset\\COVID-19_Radiography_Dataset\\predict.py");

        PyFunction pyFunction = interpreter.get("predict", PyFunction.class);
        String url = "E:\\GraduationProject\\Dataset\\COVID-19_Radiography_Dataset\\Radiography_Dataset\\Lung_Opacity\\Lung_Opacity-2312.png";
        PyObject pyObject = pyFunction.__call__(new PyString(url));
        System.out.println("java print is:"+pyObject);
    }

    @Test
    public void test2(){
        Process proc;
        PySystemState sys = Py.getSystemState();
        try {
            String url = "E:\\GraduationProject\\Dataset\\COVID-19_Radiography_Dataset\\predict.py";
            //url = "D:\\IDEA_GraduationPro\\AI_Diagnosis\\server\\predict.py";
            String img = "E:\\GraduationProject\\Dataset\\COVID-19_Radiography_Dataset\\Radiography_Dataset\\Lung_Opacity\\Lung_Opacity-2312.png";
            //String img = "D:\\IDEA_GraduationPro\\AI_Diagnosis\\server\\upload\\89977b9cbecd4606b01c7802d0ad785f.PNG";
            //String img = "C:\\Users\\63287\\Desktop\\9.PNG";
            String python = "E:\\Anaconda3\\python.exe";
            //String python = "python";
            String[] args = new String[]{python,url,img};
            System.out.println(args[2]);
            proc = Runtime.getRuntime().exec(args);
            //System.out.println(sys.argv);
            //proc = Runtime.getRuntime().exec("python E:\\GraduationProject\\Dataset\\COVID-19_Radiography_Dataset\\java_covid_predict.py");// 执行py文件
            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println(proc.waitFor());
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            System.out.println("调用python脚本并读取结果时出错："+e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3(){
        Process proc;
        try {
            PySystemState sys = Py.getSystemState();
            String url = "E:\\GraduationProject\\Dataset\\COVID-19_Radiography_Dataset\\jalynn.py";
            String img = "E:\\GraduationProject\\Dataset\\COVID-19_Radiography_Dataset\\Radiography_Dataset\\Lung_Opacity\\Lung_Opacity-2312.png";
            String python = "E:\\Anaconda3\\envs\\Pytorch_envs\\python.exe";
            int b = 2;
            String[] args = new String[]{python,url,String.valueOf(b)};
            System.out.println(args[1]);
            proc = Runtime.getRuntime().exec(args);
            //System.out.println(sys.argv);
            //proc = Runtime.getRuntime().exec("python E:\\GraduationProject\\Dataset\\COVID-19_Radiography_Dataset\\java_covid_predict.py");// 执行py文件
            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
