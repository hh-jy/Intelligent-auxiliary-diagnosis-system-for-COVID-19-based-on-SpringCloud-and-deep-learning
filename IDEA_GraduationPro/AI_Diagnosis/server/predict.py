# -*- coding: UTF-8 -*-
import sys
from torchvision import transforms
from torchvision.models import resnet18
from torch import nn
from utils import Flatten
import cv2
import torch

def labels(labels):
    text_labels = ['COVID', 'Lung_Opacity', 'Normal', 'Viral_Pneumonia']
    return str([text_labels[int(i)] for i in labels])

def predict(filename):
    device = torch.device('cuda')

    trained_model = resnet18(pretrained=True)
    model = nn.Sequential(*list(trained_model.children())[:-1],
                          Flatten(),  # [b,512,1,1] => [b,512]
                          nn.Linear(512, 4)
                          ).to(device)  # [b,512,1,1]
    model.load_state_dict(torch.load('best_noEnhance.mdl'))
    model.eval()
    file_name = filename
    # file_name = r'C:\Users\63287\Desktop\COVID-1.png'
    # file_name = r'E:\GraduationProject\Dataset\COVID-19_Radiography_Dataset\Radiography_Dataset\Normal\Normal-220.png'
    # file_name = r'E:\GraduationProject\Dataset\COVID-19_Radiography_Dataset\Radiography_Dataset\Lung_Opacity\Lung_Opacity-2312.png'  # 5743
    # file_name = r'E:\GraduationProject\Dataset\COVID-19_Radiography_Dataset\Radiography_Dataset\Viral_Pneumonia\Viral_Pneumonia-138.png'
    # file_name = r'E:\GraduationProject\Dataset\COVID-19_Radiography_Dataset\Radiography_Dataset\COVID\COVID-2605.png'
    pic = cv2.imread(file_name)
    pic = pic[:, :, ::-1]  # 将BGR格式的图片转换为RGB格式
    pic = pic.copy()

    # # cv2.imshow('show_img',pic)
    # # cv2.waitKey(0)  # 等待时间单位 ms ；参数为 0 时 无限制的等待用户的按键事件
    pic = cv2.resize(pic, dsize=(299, 299), dst=None, fx=2, fy=2, interpolation=cv2.INTER_NEAREST)
    trans = transforms.ToTensor()
    pic = trans(pic)

    # print(pic.shape)

    pic = pic.to(device)

    # pred = model(torch.tensor(torch.unsqueeze(pic, dim=0)))
    pred = model(torch.unsqueeze(pic, dim=0).clone().detach())
    # print(pred.data)
    value, y_pred = torch.max(pred.data, 1)

    # print('预测：' + str(y_pred.data.cpu().numpy()))
    y_pred = y_pred.data.cpu().numpy().tolist()
    # print(type(y_pred[0]))
    if y_pred[0] == 0:
        return labels(y_pred),"2"

    return labels(y_pred), "1"


if __name__ == '__main__':
    # response = urllib.request.urlopen('http://celestrak.com/satcat/tle.php?CATNR=25994')
    # print(response.read().decode('utf-8'))
    # print(sys.path)
    # file_name = 'C:\\Users\\63287\\Desktop\\9.PNG'
    # print(type(file_name))
    # file_name = r'E:\GraduationProject\Dataset\COVID-19_Radiography_Dataset\Radiography_Dataset\Normal\Normal-2909.png'
    # file_name = r'E:\GraduationProject\Dataset\COVID-19_Radiography_Dataset\Radiography_Dataset\Lung_Opacity\Lung_Opacity-2312.png'  # 5743
    # file_name = r'E:\GraduationProject\Dataset\COVID-19_Radiography_Dataset\Radiography_Dataset\Viral_Pneumonia\Viral_Pneumonia-138.png'
    # file_name = r'E:\GraduationProject\Dataset\COVID-19_Radiography_Dataset\Radiography_Dataset\COVID\COVID-26.png'
    # type,pred = predict("E:\\GraduationProject\\Dataset\\COVID-19_Radiography_Dataset\\Radiography_Dataset\\Lung_Opacity\\Lung_Opacity-2312.png")
    # type,pred = predict(sys.argv[1])
    # print("hello")
    # print(sys.argv)
    print(predict(sys.argv[1]))
    # print(type[2:-2])
    # print(type[2:-2],pred)