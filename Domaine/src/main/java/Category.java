public class Category
{
    private Integer id;
    private String name;
    private String image;

    public Category(int id, String name, String image)
    {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public Category(int id, String name)
    {
        this.id = id;
        this.name = name;
        this.image = null;
    }

    public Category(String name, String image)
    {
        this.id = null;
        this.name = name;
        this.image = image;
    }

    public Category(String name)
    {
        this.id = null;
        this.name = name;
        this.image = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}