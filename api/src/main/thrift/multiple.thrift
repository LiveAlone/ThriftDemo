namespace java org.yqj.thrift.api

typedef i32 int // 类似C 语言中的 宏的定义

struct Person{
  1: optional string name
  2: optional i32 age
  3: optional double grade
}

service MultiplicationService{
  int multiply (1:int n1, 2:int n2),

  int add (1:int n1, 2:int n2),

  int sub (1:int n1, 2:int n2),

  int div(1:int n1, 2:int n2),

  Person personContent(1: required i64 id),
}