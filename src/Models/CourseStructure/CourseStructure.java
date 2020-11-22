package Models.CourseStructure;

public interface CourseStructure {

    final String url = "jdbc:mysql://stusql.dcs.shef.ac.uk/team045";
    final String user = "team045" ;
    final String password = "5e15b333";

    public void add();

    public void remove();

    public boolean exists();

    public String getCode();

}
