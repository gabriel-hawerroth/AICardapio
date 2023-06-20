public class ApplicationMain {
    public static int opcao;
    public static Boolean menu = true;
    public static int opcaoContinua;

    public static void print(String texto) {
        System.out.println(texto);
    }

    public static void main(String[] args) {
        Methods.conectarBanco();
        print("Seja bem-vindo ao AiCardapio!");
        while (menu) {
            Methods.exibirMenuInicial();
            switch (opcao) {
                case 1:
                    // Chama o método CREATE
                    Methods.create();
                    break;

                case 2:
                    // Chama o método READ ONE ITEM
                    Methods.readOneItem();
                    break;

                case 3:
                    // Chama o método READ ALL ITEMS
                    Methods.readAllItems();
                    break;

                case 4:
                    // Chama o método UPDATE
                    Methods.update();
                    break;
                case 5:
                    // Chama o método DELETE
                    Methods.delete();
                    break;
                default:
                    print("Opção inválida, digite novamente");
            }
            while (opcaoContinua != 1 || opcaoContinua != 2) {
                Methods.exibirMenuContinua();                
                if (opcaoContinua == 1) {
                    break;
                } else if (opcaoContinua == 2) {
                    menu = false;
                    break;
                } else {
                    print("Opção inválida, digite novamente");
                }
            }
        }

    }

}