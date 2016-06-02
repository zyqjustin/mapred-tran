package easy.mapreduce.tran;

import java.io.IOException;

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
