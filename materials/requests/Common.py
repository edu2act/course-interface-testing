from datetime import datetime
import  time
import requests
import json


def baseUrl():
    return " http://study-perf.qa.netease.com"

def getcookies(user):
    url = "http://study-perf.qa.netease.com/common/fgadmin/login"
    header={"Content-Type":"application/json"}
    res = requests.post(url, data=json.dumps(user), headers=header)
    return res.cookies

def getCurrentTime():
    format = "%Y%m%d%H%M%S"
    return time.strftime(format)

def timeDiff(starttime, endtime):
    format = "%Y%m%d%H%M%S"
    return datetime.strptime(endtime, format) - datetime.strptime(starttime, format)

def receiverEmail():
    return "626231935@qq.com"
    # file_info = open('D:\\demo\\receiverName.txt', 'r')
    # email_address = file_info.readlines()
    # file_info.close()
    # return  email_address




