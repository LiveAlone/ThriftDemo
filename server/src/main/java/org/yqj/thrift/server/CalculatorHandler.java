package org.yqj.thrift.server;

import org.apache.thrift.TException;
import org.yqj.thrift.api.Calculator;
import org.yqj.thrift.api.InvalidOperation;
import org.yqj.thrift.api.Work;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2019-06-03
 * Email: yaoqijunmail@foxmail.com
 */
public class CalculatorHandler implements Calculator.Iface {


    @Override
    public void ping() throws TException {
        System.out.println("ping()");
    }

    @Override
    public int add(int num1, int num2) throws TException {
        System.out.println("add(" + num1 + "," + num2 + ")");
        return num1 + num2;
    }

    @Override
    public int calculate(int logid, Work w) throws InvalidOperation, TException {
        System.out.println("calculate(" + logid + ", {" + w.op + "," + w.num1 + "," + w.num2 + "})");
        int val = 0;
        switch (w.op) {
            case ADD:
                val = w.num1 + w.num2;
                break;
            case SUB:
                val = w.num1 - w.num2;
                break;
            case MUL:
                val = w.num1 * w.num2;
                break;
            case DIV:
                if (w.num2 == 0) {
                    InvalidOperation io = new InvalidOperation();
                    io.what = w.op.getValue();
                    io.why = "Cannot divide by 0";
                    throw io;
                }
                val = w.num1 / w.num2;
                break;
            default:
                InvalidOperation io = new InvalidOperation();
                io.what = w.op.getValue();
                io.why = "Unknown operation";
                throw io;
        }
        return val;
    }

    @Override
    public void zip() throws TException {
        System.out.println("zip()");
    }
}
