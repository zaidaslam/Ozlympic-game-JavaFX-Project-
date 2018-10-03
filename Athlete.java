

/**
 * @author zaid (s3590683)
 * Athlete class is used to implement Athlete type customize array list
 *
 */
public class Athlete{

    private int Index;
	private String ID;
	private String Type;
	private String Name;
	private String Age;
	private String State;
	private int points;

	/**
	 * @param Index
	 * @param ID
	 * @param Type
	 * @param Name
	 * @param Age
	 * @param State
	 * @param points
	 *            Athlete is a constructor for Athlete class
	 */
	public Athlete(int Index, String ID, String Type, String Name, String Age, String State, int points) {
		// TODO Auto-generated constructor stub

		this.Index = Index;
		this.ID = ID;
		this.Type = Type;
		this.Name = Name;
		this.Age = Age;
		this.State = State;
		this.points = points;

	}


	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getAge() {
		return Age;
	}

	public void setAge(String age) {
		Age = age;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = this.points + points;
	}

	public int getIndex() {
		return Index;
	}

	public void setIndex(int index) {
		Index = index;
	}
}
