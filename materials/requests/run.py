import time
from HTMLTestRunner import HTMLTestRunner
import unittest
from api_2019 import EmailUtils


test_dir = './testcase'
discover = unittest.defaultTestLoader.discover(test_dir, pattern='*_Test.py')
if __name__ == "__main__":
    now = time.strftime("%Y-%m-%d %H_%M_%S")
    # 定义文件名
    filename = './report/' + now + '_result.html'
     #以二进制写模式打开文件，如果没有则创建
    fp = open(filename, 'wb')
    runner = HTMLTestRunner(stream=fp,
                            title='System Interface Test Report',description='此次测试结果如下: ')
    # 运行测试套件中组装的测试用例
    runner.run(discover)
    # 关闭测试报告文件
    fp.close()

    EmailUtils.send_report()