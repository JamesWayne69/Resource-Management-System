import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ResourceDao {

public static int save(String resource_id,String name,String producer,int quantity){
	int status=0;
	try{
		Connection con=DB.getConnection();
		PreparedStatement ps=con.prepareStatement("insert into resources(resource_id,name,quantity,producer) values(?,?,?,?)");
		ps.setString(1,resource_id);
		ps.setString(2,name);
		ps.setInt(3,quantity);
		ps.setString(4,producer);
		status=ps.executeUpdate();
		con.close();
	}catch(Exception e){System.out.println(e);}
	return status;
}

public static int issueResource(String Resourcecallno,int req){
	int status=0;
	int quantity = 0;
	try{
		Connection con=DB.getConnection();
		
		PreparedStatement ps=con.prepareStatement("select quantity from resources where resource_id=?");
		ps.setString(1,Resourcecallno);
		ResultSet rs=ps.executeQuery();
		if(rs.next()){
			quantity=rs.getInt("quantity");
		}
		
		String update1 = Integer.toString(quantity-req);

		PreparedStatement ps2=con.prepareStatement("update resources set quantity=? where resource_id=?;");
		ps2.setString(1,update1);
		ps2.setString(2,Resourcecallno);
		
		status=ps2.executeUpdate();
		
		con.close();
	}catch(Exception e){System.out.println(e);}
	return status;
}

public static int procureResource(String Resourcecallno,int req){
	int status=0;
	int quantity = 0;
	try{
		Connection con=DB.getConnection();
		
		PreparedStatement ps=con.prepareStatement("select quantity from resources where resource_id=?");
		ps.setString(1,Resourcecallno);
		ResultSet rs=ps.executeQuery();
		if(rs.next()){
			quantity=rs.getInt("quantity");
		}
		
		String update1 = Integer.toString(quantity+req);

		PreparedStatement ps2=con.prepareStatement("update resources set quantity=? where resource_id=?;");
		ps2.setString(1,update1);
		ps2.setString(2,Resourcecallno);
		
		status=ps2.executeUpdate();
		
		con.close();
	}catch(Exception e){System.out.println(e);}
	return status;
}

public static boolean checkResource(String Resourcecallno){
	boolean status=false;
	try{
		Connection con=DB.getConnection();
		PreparedStatement ps=con.prepareStatement("select * from resources where resource_id=?");
		ps.setString(1,Resourcecallno);
	    ResultSet rs=ps.executeQuery();
		status=rs.next();
		con.close();
	}catch(Exception e){System.out.println(e);}
	return status;
}



}
