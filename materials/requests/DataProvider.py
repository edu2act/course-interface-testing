# -*- coding: utf-8 -*
import csv
import xlrd


def readCsV():
    data=csv.reader(open('D:\\demo\\userdata.csv','r'))
    return data
    # for user in data:
    #     print(user[0])

def readTxt():
    file_info = open('D:\\demo\\userdata.txt', 'r')
    values = file_info.readlines()
    file_info.close()
    return values

    # for item in values:
    #     data1 = item.split(',')[0]  # 第一列
    #     data2 = item.split(',')[1]  # 第二列
    #     print(data1, data2)

def readExcel(filePath,index):
    workbook = xlrd.open_workbook(filePath)
    sheet = workbook.sheet_by_index(index)
    return  sheet

    # nrows = sheet1.nrows
    # for i in range(nrows):
    #     print(sheet1.row_values(i))
if __name__=='__main__':

    sheet=readExcel(r"D:\demo\userdata.xlsx",0)
    for i in range(sheet.nrows):
        print(sheet.cell_value(i, 0) )
    #根据目录打开文件
    # workbook = xlrd.open_workbook(r"D:\demo\userdata.xlsx")
    # #根据索引获取sheet
    # sheet = workbook.sheet_by_index(0)
    # print(sheet.nrows)
    # # 遍历每一行数据
    # for i in range(sheet.nrows):
    #     print(sheet.cell_value(i,0))
