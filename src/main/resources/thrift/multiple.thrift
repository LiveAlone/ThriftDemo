namespace java org.yqj.thrift.demo

typedef i32 int // 类似C 语言中的 宏的定义

service MultiplicationService{
  int multiply(1:int n1, 2:int n2),
}