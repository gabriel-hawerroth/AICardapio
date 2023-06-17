public class ItensModel {

    private long id;
    private String nome;
    private double price;
    private String image;
    private String description;

    public ItensModel(long id, String nome, double price, String image, String description) {
        this.id = id;
        this.nome = nome;
        this.price = price;
        this.image = image;
        this.description = description;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}