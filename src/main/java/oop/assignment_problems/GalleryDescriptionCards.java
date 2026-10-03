package oop.assignment_problems;

public class GalleryDescriptionCards {

    public static void main(String[] args) {

        Painting p =
                new Painting("Sunset Fields");

        Sculpture s =
                new Sculpture("The Thinker II");

        System.out.println(p.describe());
        System.out.println(p.getPieceId());

        System.out.println(s.describe());
        System.out.println(s.getPieceId());
    }
}


// Abstract parent class
abstract class ArtPiece {

    private static int counter = 100;

    private final String pieceId;

    protected ArtPiece() {

        counter++;

        pieceId = "ART-" + counter;
    }

    public abstract String describe();

    public String getPieceId() {

        return pieceId;
    }
}


// Painting
class Painting extends ArtPiece {

    private String title;

    public Painting(String title) {

        super();

        this.title = title;
    }

    @Override
    public String describe() {

        return "Painting: "
                + title
                + ", framed on canvas";
    }
}


// Sculpture
class Sculpture extends ArtPiece {

    private String title;

    public Sculpture(String title) {

        super();

        this.title = title;
    }

    @Override
    public String describe() {

        return "Sculpture: "
                + title
                + ", carved from stone";
    }
}