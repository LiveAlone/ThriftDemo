package org.yqj.thrift.server;

import org.apache.thrift.TException;
import org.yqj.thrift.api.MultiplicationService;
import org.yqj.thrift.api.Person;

/**
 * Created by yaoqijun on 2018/8/16.
 * email: yaoqijunmail@foxmail.com
 */
public class MultiplicationHandler implements MultiplicationService.Iface{
    @Override
    public int multiply(int n1, int n2) throws TException {
        System.out.println("current thread execution name is : " + Thread.currentThread().getName());
        return n1 * n2;
    }

    @Override
    public int add(int n1, int n2) throws TException {
        return n1 + n2;
    }

    @Override
    public int sub(int n1, int n2) throws TException {
        return n1 - n2;
    }

    @Override
    public int div(int n1, int n2) throws TException {
        return n1 / n2;
    }

    @Override
    public Person personContent(long id) throws TException {
        Person person = new Person();
        person.setName("yaoqijun");
        person.setAge(100);
        person.setGrade(123.123);
        return person;
    }
}
