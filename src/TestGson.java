package bank;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

public class TestGson {
	public static void main(String args[]) {
//		String json="{\"code\":\"0000\",\"msg\":\"成功\",\"data\":[{\"userid\":\"1\",\"userName\":\"张三\"},{\"userid\":\"1\",\"userName\":\"王五\"}]}";
		String json="{\"code\":\"0000\",\"msg\":\"成功\",\"data\":{\"userid\":\"1\",\"userName\":\"张三\"}}";
		JsonModel<List<User>> j=new  JsonModel<List<User>>().toJsonModel(json);
		
		System.out.println(j.getData());
	}

	 
}

class User implements Serializable {
	private String userid;
	private String userName;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}

class ParameterizedTypeImpl implements ParameterizedType {
	Type[] type;
	Class raw;

	@Override
	public Type[] getActualTypeArguments() {
		// TODO 自动生成的方法存根
		return type;
	}

	@Override
	public Type getOwnerType() {
		// TODO 自动生成的方法存根
		return null;
	}

	public ParameterizedTypeImpl(Type[] args, Class raw) {
		super();
		this.type = args != null ? args : new Type[0];
		this.raw = raw;
	}

	@Override
	public Type getRawType() {
		// TODO 自动生成的方法存根
		return raw;
	}

}
//abstract class JsonModelSuper<T>{
//	public JsonModel<T> toJsonModel(String json) {
////		this.getClass().getGenericSuperclass()
//		Type type = getClass().getGenericSuperclass();
//		Type[] t = ((ParameterizedType) type).getActualTypeArguments();
//		Type ty = new ParameterizedTypeImpl(t, JsonModel.class);
//		return new Gson().fromJson(json, ty);
//	}
//};

abstract class  JsonModels<T>  {



	private String msg;
	private String code;
	private T data;

	 
	public String getMsg() {
		return msg;
	}
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	 
	public JsonModel<T> toJsonModel(String json) {
		Type type = getClass().getGenericSuperclass();
		Type[] t = ((ParameterizedType) type).getActualTypeArguments();
		Type ty = new ParameterizedTypeImpl(t, JsonModel.class);
		return new Gson().fromJson(json, ty );
	}
	
	
}
class JsonModel<T> extends JsonModels<T>{}
