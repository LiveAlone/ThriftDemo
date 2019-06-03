
namespace java org.yqj.thrift.api

struct Example{
  1:i32 number = 10,
  2:i64 bigNumber = 20,
  3:double decimals,
  4:string name = 'thrift'
}

const i32 INT32CONSTANT = 9853
const map<string, string> MAPCONSTANT = {'hello':'world', 'goodnight':'moon'}

enum Operation{
  ADD = 1
  SUB = 2
  MUL = 3
  DIV = 4
}

struct Work{
  1: required i32 num1 = 0
  2: required i32 num2 = 0
  3: required Operation op
  4: optional string comment
}

exception InvalidOperation {
  1: i32 what,
  2: string why
}

service Calculator {
   void ping(),

   i32 add(1:i32 num1, 2:i32 num2),

   i32 calculate(1:i32 logid, 2:Work w) throws (1:InvalidOperation ouch),

   oneway void zip()
}