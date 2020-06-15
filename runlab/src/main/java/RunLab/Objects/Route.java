package RunLab.Objects;

public class Route {
    
    public Polyline summary_line = new Polyline("");
    public Polyline detailed_line = new Polyline("");

    public Route(Polyline s, Polyline d){
        this.summary_line = s;
        this.detailed_line = d;
    }

}