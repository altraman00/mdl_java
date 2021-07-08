package com.mdl.proto.controller;

import com.google.protobuf.InvalidProtocolBufferException;
import com.googlecode.protobuf.format.JsonFormat;
import com.mdl.proto.common.BaseResponse;
import com.mdl.proto.common.ResultCode;
import com.mdl.proto.model.ReturnVOProto;
import com.mdl.proto.model.ReturnVOProto.ReturnVO;
import com.mdl.proto.model.UserEntity;
import com.mdl.proto.model.UserProto.UserProResp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Project : mdl-demo
 * @Package Name : com.mdl.proto.controller
 * @Author : xiekun
 * @Create Date : 2020年11月27日 18:03
 * ----------------- ----------------- -----------------
 */

@RequestMapping("open")
@RestController
public class UserController {

  @GetMapping("/hello")
  public UserProResp hello() {
    UserEntity userEntity = new UserEntity();
    userEntity.setName("zhangsan");
    userEntity.setAge("18");
    userEntity.setSex("man");
//    //序列化成ProtoBuf数据结构
//    byte[] userProtoObj = ProtoBufUtil.serializer(userEntity);
//    //ProtoBuf数据结构反序列化成User对象
//    UserEntity user = ProtoBufUtil.deserializer(userProtoObj, UserEntity.class);
    return UserProResp.newBuilder()
        .setErrCode(1001)
        .setErrMsg("xxxx")
        .setUserInfo(userEntity.toProto())
        .build();
  }


  /**
   * 逻辑内使用protobuf，可正常打印
   * @return
   */
  @GetMapping("/hello2")
  public void hello2() {
    System.out.println("===== 构建一个returnVO模型开始 =====");
    ReturnVOProto.ReturnVO.Builder builder = ReturnVOProto.ReturnVO.newBuilder();
    builder.setCode("200");
    builder.setData("OK");
    builder.setMessage("GO");

    ReturnVOProto.ReturnVO returnVO = builder.build();
    System.out.println(returnVO.toString());
    System.out.println("===== 构建returnVO模型结束 =====");

    System.out.println("===== returnVO Byte 开始=====");
    for (byte b : returnVO.toByteArray()) {
      System.out.print(b);
    }
    System.out.println("\n" + "returnVO" + returnVO.toByteString().size());
    System.out.println("===== returnVO Byte 结束 =====");

    System.out.println("===== returnVO 反序列化生成对象开始 =====");
    ReturnVOProto.ReturnVO returnVO1 = null;
    try {
      returnVO1 = ReturnVOProto.ReturnVO.parseFrom(returnVO.toByteArray());
    } catch (InvalidProtocolBufferException e) {
      e.printStackTrace();
    }
    System.out.print(returnVO1.toString());
    System.out.println("===== returnVO 反序列化生成对象结束 =====");
  }


  /**
   * 封装返回带code的json
   * @return
   */
  @GetMapping("/hello3")
  public BaseResponse<UserEntity> hello3() {
    UserEntity userEntity = new UserEntity();
    userEntity.setName("zhangsan");
    userEntity.setAge("18");
    userEntity.setSex("man");
    return new BaseResponse<>(ResultCode.SUCCESS, userEntity);
  }


  /**
   * 返回protobuf报错
   * @return
   */
  @GetMapping("/hello4")
  public BaseResponse<ReturnVO> hello4() {
    System.out.println("===== 构建一个returnVO模型开始 =====");
    ReturnVOProto.ReturnVO.Builder builder = ReturnVOProto.ReturnVO.newBuilder();
    builder.setCode("200");
    builder.setData("OK");
    builder.setMessage("GO");
    ReturnVOProto.ReturnVO returnVO = builder.build();
    return new BaseResponse<>(ResultCode.SUCCESS, returnVO);
  }

  /**
   * 直接返回json
   * @return
   */
  @GetMapping("/hello5")
  public UserEntity hello5() {
    UserEntity userEntity = new UserEntity();
    userEntity.setName("zhangsan");
    userEntity.setAge("18");
    userEntity.setSex("man");
    return userEntity;
  }


  /**
   * 通过protobuf工具将proto转换为json
   * @return
   */
  @GetMapping("/hello6")
  public String hello6() {
    System.out.println("===== 构建一个returnVO模型开始 =====");
    ReturnVOProto.ReturnVO.Builder builder = ReturnVOProto.ReturnVO.newBuilder();
    builder.setCode("200");
    builder.setData("OK");
    builder.setMessage("GO");
    ReturnVOProto.ReturnVO returnVO = builder.build();
    String jsonFormat = JsonFormat.printToString(returnVO);
    return jsonFormat;
  }


}
