package com.qubole.hive.udftest;

import com.qubole.stringutils.StringUtils;
import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentLengthException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorConverter.StringConverter;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.StringObjectInspector;

@Description(
    name = "udftest",
    value = "_FUNC_(str) - Returns str converted to upper case"
)
public class UDFTest extends GenericUDF {
  PrimitiveObjectInspector argumentOI;
  StringConverter stringConverter;
  StringUtils stringUtils;

  @Override
  public ObjectInspector initialize(ObjectInspector[] objectInspectors) throws UDFArgumentException {
    if (objectInspectors.length != 1) {
      throw new UDFArgumentLengthException("Length of arguments should be 1");
    } else if (!(objectInspectors[0] instanceof StringObjectInspector)) {
      throw new UDFArgumentTypeException(0, "Argument should be of type string");
    }
    this.argumentOI = (PrimitiveObjectInspector)objectInspectors[0];
    this.stringConverter = new StringConverter(this.argumentOI);
    return PrimitiveObjectInspectorFactory.getPrimitiveJavaObjectInspector(PrimitiveObjectInspector.PrimitiveCategory.STRING);
  }

  @Override
  public Object evaluate(DeferredObject[] deferredObjects) throws HiveException {
    String val = null;
    if (deferredObjects[0] != null) {
      val = (String) stringConverter.convert(deferredObjects[0].get());
    }
    stringUtils = new StringUtils(val);
    return stringUtils.upper();
  }

  @Override
  public String getDisplayString(String[] strings) {
    assert strings.length == 1;
    return "UDFTest(" + strings[0] + ")";
  }
}
