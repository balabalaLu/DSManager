package com.Dormitory;

import com.Dormitory.jdbc.jdbcAdmin;
import com.Dormitory.jdbc.jdbcDormitory;
import com.Dormitory.jdbc.jdbcLogin;
import com.Dormitory.model.AdminPo;
import com.Dormitory.model.RoomPo;
import com.Dormitory.model.StudentPo;

import java.util.List;

public class test{
    public static void main(String[] args){

        //超级管理员登录及权限验证
        jdbcLogin login= new jdbcLogin();

        System.out.println("===root===");
        int permission1=login.queryForLogin("root","root123");

        if(permission1==2){//判断是否为超级管理员权限
            jdbcAdmin root_op=new jdbcAdmin();
            //增加用户
            System.out.println("===增加用户===");
            int flag1=root_op.addAdmin("03113","admin1","吴彩霞","23333");
            if (flag1>0)
                System.out.println("添加成功！");
            else
                System.out.println("添加失败");
            //删除用户
            System.out.println("===删除用户===");
            String id="03112";
            if (id!="root"){
                //限制操作
                int flag2=root_op.deleteAdmin(id);
                if (flag2>0)
                    System.out.println("删除成功！");
                else
                    System.out.println("删除失败");
            }
            //修改用户信息
            System.out.println("===修改用户信息===");
            int flag3=root_op.updateAdmin("03111","admin1","刘桂华","23333");
            if (flag3>0)
                System.out.println("更新成功！");
            else
                System.out.println("更新失败");

            //查询用户信息:以username为例
            System.out.println("===查询用户信息===");
            AdminPo adminPo=root_op.getAdminPoByUserid("03111");
            String username=adminPo.getUsername();
            System.out.println(username);

        }
        else if (permission1==0)
            System.out.println("登陆失败，无法访问");


        //普通用户操作验证
        System.out.println("===密码错误验证===");
        int permission2=login.queryForLogin("03111","123456");
        System.out.println("===普通用户===");
        int permission3=login.queryForLogin("03111","admin1");

        jdbcDormitory user_op=new jdbcDormitory();


        if (permission3==0)
            System.out.println("登陆失败，无法访问");
        else{
            //增加学生
            System.out.println("===增加用户===");
            int flag1=user_op.addStudent("2016300010234","409");
            if (flag1>0)
                System.out.println("添加成功！");
            else
                System.out.println("添加失败");

            //删除学生
            System.out.println("===删除学生===");
            int flag2=user_op.deleteStudent("2016300010233","409");
            if (flag2>0)
                System.out.println("删除成功！");
            else
                System.out.println("删除失败");


            //通过寝室号查询宿舍的基本信息
            System.out.println("===查询宿舍===");
            RoomPo roomPo=user_op.getRoomPoByRno("301");
            String price=roomPo.getPrice();
            System.out.println(price);

            //通过寝室号查询宿舍的住宿的学生的基本信息
            System.out.println("===查询学生===");
            List<StudentPo> studentPo =user_op.getStudentPoByRno("409");

//            String price=roomPo.getPrice();
//            System.out.println(price);

        }








     }




//        jdbcAdmin test2=new jdbcAdmin();
//        jdbcDormitory test3=new jdbcDormitory();
//
//        int flag=test2.deleteAdmin("0311");
//
//
//        System.out.println(flag);

}
