周灵犀-1=[2, 4, 6]   
黄鑫辉-2=[1, 2, 5]   
丁增玉尼-5=[1, 3, 4, 6]   
方超群-2=[1, 3, 4, 5]   
王晨阳-1=[1, 2, 4, 6]   
次仁扎西-3=[2, 3, 6]

改变数据：
周灵犀-1=[12, 14, 16]   
黄鑫辉-2=[21, 22, 25]   
丁增玉尼-5=[51, 53, 54, 56]   
方超群-2=[21, 23, 24, 25]   
王晨阳-1=[11, 12, 14, 16]   
次仁扎西-3=[32, 33, 36]

取出编号排序，不重复

11、12、14、16、21、22、24、25、32、33、36、51、53、54、56

取出每个编号出现的次数：

11=1
12=2
14=2
16=2
21=2
22=1
24=1
25=2
32=1
33=1
36=1
51=1
53=1
54=1
56=1

排序：编号升序、值降序

12=2
14=2
16=2
21=2
25=2

11=1
22=1
24=1
32=1
33=1
36=1
51=1
53=1
54=1
56=1

for 循环 编号

当前数据为：
周灵犀-1=[12, 14, 16]   
黄鑫辉-2=[21, 22, 25]   
丁增玉尼-5=[51, 53, 54, 56]   
方超群-2=[21, 23, 24, 25]   
王晨阳-1=[11, 12, 14, 16]   
次仁扎西-3=[32, 33, 36]

第一个  12，找出 课程里面出现过12 的前5个人
=周灵犀、王晨阳
移除周灵犀、王晨阳，

当前数据为:
黄鑫辉-2=[21, 22, 25]   
丁增玉尼-5=[51, 53, 54, 56]   
方超群-2=[21, 23, 24, 25]   
次仁扎西-3=[32, 33, 36]


找第二名 14，没有找到，找第三名16...又没有
找第4名，21
黄鑫辉、方超群

找到的人=周灵犀、王晨阳、黄鑫辉、方超群
当前数据为：
丁增玉尼-5=[51, 53, 54, 56]   
次仁扎西-3=[32, 33, 36]
还是不够5个

继续找21后面的22没有，24没有，找32，
找到一个：次仁扎西

凑齐5个，周一的人为：周灵犀、王晨阳、黄鑫辉、方超群、次仁扎西

当前数据为：
丁增玉尼-5=[51, 53, 54, 56]   
找周二的：
33、36木有人，
找51，找到
丁增玉尼

没有数据了，结束任务

最终结果：
周一的人为：周灵犀、王晨阳、黄鑫辉、方超群、次仁扎西
周二的人为：丁增玉尼