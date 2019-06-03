namespace java org.yqj.thrift.api

typedef i32 int // 类似C 语言中的 宏的定义

service MultiplicationService{
  int multiply (1:int n1, 2:int n2),

  int add (1:int n1, 2:int n2),

  int sub (1:int n1, 2:int n2),

  int div(1:int n1, 2:int n2),
}