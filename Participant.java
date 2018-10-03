
/**
 * @author @author zaid (s3590683) Participant class is used to implement
 *         participant type customize array list
 *
 */
public class Participant {

	private String ID;
	private String Type;
	private String Name;
	private String Age;
	private String State;
	private int Points;

	/**
	 * @param ID
	 * @param Type
	 * @param Name
	 * @param Age
	 * @param State
	 * @param Points
	 *            Participant is a constructor for Participant class
	 */
	public Participant(String ID, String Type, String Name, String Age, String State, int Points) {
		// TODO Auto-generated constructor stub

		this.ID = ID;
		this.Type = Type;
		this.Name = Name;
		this.Age = Age;
		this.State = State;
		this.Points = Points;
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
		return Points;
	}

	public void setPoints(int points) {
		Points = points;
	}
}