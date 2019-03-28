# ultrasonic
通过hook的方式来监控代码运行时的一些问题。
由于这种hook关键在于找到hook点，进行一些检测，所以命名为超声波寓意像超声波能深入人体内部一样来检测程序的问题。

hook底层使用yahfa，同时也作出了抽象，可以替换yahfa用别的hook库取代。
目前内置Bitmap创建，线程创建和主线程Looper的监控，监控的结果以打印的形式输出。

如果要自定义监控也很简单，可参考Bitmap监控和线程监控，对应的类分别为BitmapExamination和ThreadExamination。
主要的类有Probe（探头），Examination（检测单）和Doctor（医生）。
