import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;


public class App {

	public static void main(String[] args) throws IOException{
	
		BufferedReader inp=new BufferedReader(new InputStreamReader(System.in));
		String[] nots=inp.readLine().split(" ");
		int noc=0;
		int no_trees=Integer.parseInt(nots[0]);
		int no_students=Integer.parseInt(nots[1]);
		BSTFilesBuilder bfb=new BSTFilesBuilder();
		bfb.createBSTFiles(no_students,no_trees);
		boolean[] gottree=new boolean[no_students];
		FileWriter fw=new FileWriter("output.txt");
		BufferedWriter bw=new BufferedWriter(fw);
		int rollno;
		String[] answers=new String[no_students];
		
		for (int i=0; i<no_trees; i++){
			String filename=(i+1)+".txt";
			FileReader fr=new FileReader(filename);
			BufferedReader br=new BufferedReader(fr);
			String type=br.readLine();
			int nob=Integer.parseInt(br.readLine());
			
			if (type.equals("String")){
				ChristmasTree<String> c=new ChristmasTree<String>();
				String[] keys=br.readLine().split(" ");
				for (int j=0; j<no_students; j++){
					c.insert(keys[j]);
				}
				rollno=c.calrootpos(c.root);
				gottree[rollno-1]=true;
				if (answers[rollno-1]==null){
					answers[rollno-1]=rollno+" "+c.Stringans(c.root);
				}
				else{
					answers[rollno-1]=answers[rollno-1]+" "+c.Stringans(c.root);
				}
				
			}
			
			else if (type.equals("Float")){
				ChristmasTree<Float> c=new ChristmasTree<Float>();
				String[] keys=br.readLine().split(" ");
				for (int j=0; j<no_students; j++){
					c.insert(Float.parseFloat(keys[j]));
				}
				
				rollno=c.calrootpos(c.root);
				gottree[rollno-1]=true;
				if (answers[rollno-1]==null){
					answers[rollno-1]=rollno+" "+c.floatans(c.root);
				}
				else{
					answers[rollno-1]=answers[rollno-1]+" "+c.floatans(c.root);
				}
			}
			
			else{
				ChristmasTree<Integer> c=new ChristmasTree<Integer>();
				String[] keys=br.readLine().split(" ");
				for (int j=0; j<no_students; j++){
					c.insert(Integer.parseInt(keys[j]));
				}
				rollno=c.calrootpos(c.root);
				gottree[rollno-1]=true;
				if (answers[rollno-1]==null){
					answers[rollno-1]=rollno+" "+c.intans(c.root);
				}
				else{
					answers[rollno-1]=answers[rollno-1]+" "+c.intans(c.root);
				}
			}
		}
		for(int k=0; k<no_students; k++){
			if (gottree[k]){
				bw.write(answers[k]+"\n");
			}
			else{
				noc++;
			}
		}
		bw.write(noc);
		
		}
}

class ChristmasTree<t>{
	ball<t> root;
	int rootpos=1;
	t sum;
	
	ChristmasTree(){
		this.root=null;
	}
	
	void insert(t key){
		root=Insert(root,key);
	}
	
	ball<t> Insert(ball<t> root, t key){
		ball<t> b1=new ball(key);
		if (root==null){
			return b1;
		}
		else if (b1.compare(root)<0){
			root.left=Insert(root.left,key);
		}
		else if (b1.compare(root)>0){
			root.right=Insert(root.right,key);
		}
		return root;
	}
	
	int calrootpos(ball<t> root){
		if (root==null){
			return 0;
		}
		
		int pos=calrootpos(root.left);
		pos++;
		pos=pos+calrootpos(root.right);
		return pos;
	}
	
	float floatans(ball<t> root){
		if (root==null){
			return (float)0;
		}
		return (floatans(root.left)+(float)root.key+floatans(root.right));
	}

	int intans(ball<t> root) {
		// TODO Auto-generated method stub
		if (root==null){
			return 0;
		}
		return (intans(root.left)+(int)root.key+intans(root.right));
	}

	String Stringans(ball<t> root) {
		if (root==null){
			return "";
		}
		return (Stringans(root.left)+(String)root.key+Stringans(root.right));
	}
}

class ball<t> implements comparable<ball<t>>{
	t key;
	ball<t> left;
	ball<t> right;
	
	ball(t k){
		key=k;
	}

	@Override
	public int compare(ball<t> other) {
		if (this.key instanceof String && other.key instanceof String){
			return (((String) this.key).compareTo((String)other.key));
		}
		else if (this.key instanceof Float && other.key instanceof Float){
			return (((Float)this.key).compareTo((Float)other.key));
		}
		else{
			return (((Integer)this.key).compareTo((Integer)other.key));
		}
		
	}
	
}


