package RunLab.Objects;

public class Route {
    
    public Polyline summary_line = new Polyline("");
    public Polyline detailed_line = new Polyline("");
    public Coord[] detailed_coords = new Coord[0];

    public Route(Polyline s, Polyline d){
        this.summary_line = s;
        this.detailed_line = d;
        // polyline, snap to map, coords
    }

}