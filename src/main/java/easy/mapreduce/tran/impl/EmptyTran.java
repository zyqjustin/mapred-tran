package easy.mapreduce.tran.impl;

import java.io.IOException;

import easy.mapreduce.tran.Tran;

public class EmptyTran {
	
	private EmptyTran() {}
	
	@SuppressWarnings("unchecked")
	public static <FROM, TO> Tran<FROM, TO> getInstance() {
		return new Tran<FROM, TO>() {

			@Override
			public TO from(FROM from) throws IOException {
				return (TO) from;
			}

			@Override
			public FROM to(TO to) throws IOException {
				return (FROM) to;
			}
			
		};
	}

}
