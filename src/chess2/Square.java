package chess2;

public class Square{
	private int[] FileRank = new int[2];
	private String name;
	
	public Square(int Rank, int File) {
		setFileRank(Rank, File);
		setName();
	}
	public Square(String name) {
		setFileRank(name);
		this.name=name.toUpperCase();
	}
	public Square(int[] FileRank) {
		setFileRank(FileRank);
		setName();
	}
	
	public int[] getFileRank() {
		return FileRank.clone();
	}
	public void setFileRank(int File, int Rank) {
		if (File<0 || File>7 || Rank<0 || Rank>7) throw new IllegalArgumentException();
		this.FileRank[0]=File;
		this.FileRank[1]=Rank;
		setName();
	}
	public void setFileRank(String name) {
		if (name.length()!=2) throw new IllegalArgumentException();
		int File = (int)(name.toUpperCase().charAt(0)-'A');
		int Rank = (int)(name.toUpperCase().charAt(1)-'1');
		setFileRank(File, Rank);
	}
	public void setFileRank(int[] FileRank) {
		setFileRank(FileRank[0], FileRank[1]);	
	}
	@Override
	public String toString() {
		return name;
	}
	private void setName() {
		this.name = (char)(FileRank[0]+'A') +""+ (FileRank[1]+1);
	}
	
	public boolean equals(Square square) {
		return square.name.equalsIgnoreCase(this.name);
	}
	
}
