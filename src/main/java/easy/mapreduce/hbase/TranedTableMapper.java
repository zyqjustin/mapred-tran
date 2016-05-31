package easy.mapreduce.hbase;

import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.io.WritableComparable;

import easy.mapreduce.tran.Tran;

public abstract class TranedTableMapper<KEY, VALUE, KEYOUT extends WritableComparable<? super KEYOUT>, VALUEOUT extends WritableComparable<? super VALUEOUT>> extends TableMapper<KEYOUT, VALUEOUT>{

	protected Context context;
	private Tran<KEY, KEYOUT> keyTran;
	private Tran<VALUE, VALUEOUT> valueTran;
	
	//TODO init trans
	private void init() {

	}
}
