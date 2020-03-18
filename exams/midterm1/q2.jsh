/open q2.java
class Test implements TypeCaster<Integer,Integer> {	 	
@Override	 	
public Integer cast(Integer i) { 	 	
    return new Integer(0); 	 	
}	 	
}	 	
TypeCaster t	 	
ToString ts	 	
Round r	 	
ToList tl	 	
t = ts	 	
t = r	 	
t = tl	 	
String s1 = new ToString<>().cast(123)	 	
String s2 = new ToString<>().cast(List.of(1,2,3))	 	
String s3 = new ToString<>().cast(Map.of("COM1",117417))	 	
s1	 
s2	 	
s3	 	
int i1 = new Round().cast(3.1415)	 	
i1	 	
int i2 = new Round().cast(7.9)	 	
i2	 	
List<Integer> list1 = new ToList<Integer>().cast(new Integer[]{1,2,3})	 	
List<String> list2 = new ToList<String>().cast(new String[]{"Test1","Test2"})	 	
list1	 	
list2	 	
List<Number> listNumber = new ToList<Number>().cast(new Number[]{1,3.0})	 	
List<String> listC = ListCaster.castList(listNumber, new ToString<Number>()) 	 	
listNumber	 	
listC	 	
List<Object> listObject = new ToList<Object>().cast(new Object[]{1,3.0,"Test1"})	 	
List<String> listC2 = ListCaster.castList(listObject, new ToString<Object>())	 	
listObject	 	
listC2	 	
/exit