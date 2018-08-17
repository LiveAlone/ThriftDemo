package org.yqj.thrift.demo.simple;

import org.apache.thrift.TException;

/**
 * Created by yaoqijun on 2018/8/16.
 * email: yaoqijunmail@foxmail.com
 */
public class MultiplicationHandler implements MultiplicationService.Iface{
    @Override
    public int multiply(int n1, int n2) throws TException {
        return n1 * n2;
    }
}
