struct Example{
  1:i32 number = 10,
  2:i64 bigNumber = 20,
  3:double decimals,
  4:string name = 'thrift'
}

exception InvalidOperation {
  1: i32 what,
  2: string why
}

service StringCache {
  void setContent(1:i32 key, 2:string value),
  string getContent(1:i32 key) throws (1:InvalidOperation knf),
  void deleteContent(1:i32 key),
}

enum Operation {
  ADD = 1,
  SUBTRACT = 2,
  MULTIPLY = 3,
  DIVIDE = 4
}
