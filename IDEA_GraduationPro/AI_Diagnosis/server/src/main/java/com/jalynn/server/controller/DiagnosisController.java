package com.jalynn.server.controller;

import com.jalynn.server.VO.PredictResultVO;
import com.jalynn.server.VO.ResultVO;
import com.jalynn.server.enums.ResultEnum;
import com.jalynn.server.enums.ResultTypeEnum;
import com.jalynn.server.enums.TypeEnum;
import com.jalynn.server.util.ResultVOUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.python.core.Py;
import org.python.core.PySystemState;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/diagnosis")
public class DiagnosisController {

    @PostMapping("/predict")
    public ResultVO predict(MultipartFile file) throws IOException, InterruptedException {
        PredictResultVO predictResultVO = new PredictResultVO();
        String result = "";

        if (file != null){
            String path = System.getProperty("user.dir")+"\\server\\upload";
            File realPath = new File(path);
            if (!realPath.exists()){
                realPath.mkdir();
            }
            //处理文件名 UUID拼串=uuid+"."+扩展名
            String s = UUID.randomUUID().toString().replace("-", "") + "." + FilenameUtils.getExtension(file.getOriginalFilename());
            //调用业务 文件拷贝
            //upload.transferTo(new File(realPath,s));出现错误原因：出现这个问题的原因是在上传文件中获取文件的数据时采用了异步，Controller提前结束了，临时文件会被删除
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path+"\\"+s));
            String filePath = path+"\\"+s;//图片路径
            //System.out.println("filePath:"+filePath);
            String s1 = filePath.replaceAll("\\\\", "\\\\\\\\");
            String img = s1.replaceAll("/", "\\\\\\\\");

            Process proc;
            //try {
            //String url = "E:\\GraduationProject\\Dataset\\COVID-19_Radiography_Dataset\\predict.py";
            String url = "D:\\IDEA_GraduationPro\\AI_Diagnosis\\server\\predict.py";

            String python = "E:\\Anaconda3\\python.exe";
            String[] args = new String[]{python,url,img};
            //System.out.println("img:"+args[2]);
            proc = Runtime.getRuntime().exec(args);

            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                //System.out.println(line);
                result = line;
            }
            //System.out.println(proc.waitFor());
            //success = ResultVOUtil.success(line);
            in.close();
            proc.waitFor();

            //因为暂存在upload文件夹中才能获取其相应的路径,所以图片上传后也要删除,以免占据内存空间
            File delFile = new File(path);
            File[] allFiles = delFile.listFiles();
            if (allFiles!=null){
                for(int i =0 ;i< allFiles.length;i++){
                    allFiles[i].delete();
                }
            }

            List<String> list = new ArrayList<>();
            Pattern p = Pattern.compile("'([^']*)'");
            Matcher m = p.matcher(result);
            while (m.find()) {
                System.out.println(m.group(0));
                list.add(m.group(0).substring(1,m.group(0).length()-1));
            }
            if (list.get(0).equals(TypeEnum.COVID.getCode())){
                predictResultVO.setDiagType(TypeEnum.COVID.getMessage());
                predictResultVO.setDiagResult(ResultTypeEnum.POSITIVE.getMessage());
            }
            else if(list.get(0).equals(TypeEnum.Lung_Opacity.getCode())){
                predictResultVO.setDiagType(TypeEnum.Lung_Opacity.getMessage());
                predictResultVO.setDiagResult(ResultTypeEnum.NEGATIVE.getMessage());
            }else if(list.get(0).equals(TypeEnum.Normal.getCode())){
                predictResultVO.setDiagType(TypeEnum.Normal.getMessage());
                predictResultVO.setDiagResult(ResultTypeEnum.NEGATIVE.getMessage());
            }else if(list.get(0).equals(TypeEnum.Viral_Pneumonia.getCode())){
                predictResultVO.setDiagType(TypeEnum.Viral_Pneumonia.getMessage());
                predictResultVO.setDiagResult(ResultTypeEnum.NEGATIVE.getMessage());
            }

            return ResultVOUtil.success(predictResultVO);
        }

        return ResultVOUtil.error(ResultEnum.DIAGNOSIS_NULL_ERROR);
    }

    /*
     * 上传图片到指定路径
     * */
    //@RequestParam("file") 将name=file控件得到的文件封装成CommonsMultipartFile 对象
    //批量上传CommonsMultipartFile则为数组即可
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String upload(MultipartFile file) throws IOException {
        String path = System.getProperty("user.dir")+"/upload";
        File realPath = new File(path);
        if (!realPath.exists()){
            realPath.mkdir();
        }
        //处理文件名 UUID拼串=uuid+"."+扩展名
        String s = UUID.randomUUID().toString().replace("-", "") + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        System.out.println(s);
        //调用业务 文件拷贝
        //upload.transferTo(new File(realPath,s));出现错误原因：出现这个问题的原因是在上传文件中获取文件的数据时采用了异步，Controller提前结束了，临时文件会被删除
        FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path+"/"+s));
        return s;
    }

    @GetMapping("/test")
    @Transactional
    public String test() throws IOException, InterruptedException {
        Process proc;

        PySystemState sys = Py.getSystemState();
            String url = "E:\\GraduationProject\\Dataset\\COVID-19_Radiography_Dataset\\predict.py";
            url = "D:\\IDEA_GraduationPro\\AI_Diagnosis\\server\\predict.py";
            String img = "E:\\GraduationProject\\Dataset\\COVID-19_Radiography_Dataset\\Radiography_Dataset\\Lung_Opacity\\Lung_Opacity-2312.png";
            //String img = "D:\\IDEA_GraduationPro\\AI_Diagnosis\\server\\upload\\33777aac89594ca4b605360ac379756d.PNG";
            //String img = "C:\\Users\\63287\\Desktop\\9.PNG";
            //String python = "E:\\Anaconda3\\envs\\Pytorch_envs\\python.exe";
            String python = "python";
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
        //} catch (IOException e) {
        //    System.out.println("调用python脚本并读取结果时出错："+e.getMessage());
        //    e.printStackTrace();
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
        return "hello "+line;
    }

    @GetMapping("/test2")
    public ResultVO test2() throws IOException, InterruptedException {
        PredictResultVO predictResultVO = new PredictResultVO();
        Process proc;
        String result = "";
        //String python = "python";
        String python = "E:\\Anaconda3\\python.exe";
        String img = "E:\\GraduationProject\\Dataset\\COVID-19_Radiography_Dataset\\Radiography_Dataset\\Lung_Opacity\\Lung_Opacity-2312.png";
        String url = "D:\\IDEA_GraduationPro\\AI_Diagnosis\\server\\predict.py";
        String[] args = {python, url,img};
        proc = Runtime.getRuntime().exec(args);
        //System.out.println(sys.argv);
        //proc = Runtime.getRuntime().exec("python E:\\GraduationProject\\Dataset\\COVID-19_Radiography_Dataset\\java_covid_predict.py");// 执行py文件
        //用输入输出流来截取结果
        BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        String line = null;
        while ((line = in.readLine()) != null) {
            System.out.println("line:"+line);
            result = line;
        }
        //result = line;
        System.out.println("result:"+result);
        System.out.println(proc.waitFor());
        in.close();
        proc.waitFor();

        List<String> list = new ArrayList<>();
        Pattern p = Pattern.compile("'([^']*)'");
        Matcher m = p.matcher(result);
        while (m.find()) {
            System.out.println(m.group(0));
            list.add(m.group(0).substring(1,m.group(0).length()-1));
        }
        if (list.get(0).equals(TypeEnum.COVID.getCode())){
            predictResultVO.setDiagType(TypeEnum.COVID.getMessage());
            predictResultVO.setDiagResult(ResultTypeEnum.POSITIVE.getMessage());
        }
        else if(list.get(0).equals(TypeEnum.Lung_Opacity.getCode())){
            predictResultVO.setDiagType(TypeEnum.Lung_Opacity.getMessage());
            predictResultVO.setDiagResult(ResultTypeEnum.NEGATIVE.getMessage());
        }else if(list.get(0).equals(TypeEnum.Normal.getCode())){
            predictResultVO.setDiagType(TypeEnum.Normal.getMessage());
            predictResultVO.setDiagResult(ResultTypeEnum.NEGATIVE.getMessage());
        }else if(list.get(0).equals(TypeEnum.Viral_Pneumonia.getCode())){
            predictResultVO.setDiagType(TypeEnum.Viral_Pneumonia.getMessage());
            predictResultVO.setDiagResult(ResultTypeEnum.NEGATIVE.getMessage());
        }

        return ResultVOUtil.success(predictResultVO);
    }

}
