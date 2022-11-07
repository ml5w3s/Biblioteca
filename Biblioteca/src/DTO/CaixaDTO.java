package DTO;

public class CaixaDTO {
  int id;
  String nomeCaixa;
  

    public CaixaDTO(){
        
    }
    
    public CaixaDTO(Integer id, String nomeCaixa){
      this.id=id;
      this.nomeCaixa=nomeCaixa;
    }
  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCaixa() {
        return nomeCaixa;
    }

    public void setNomeCaixa(String nomeCaixa) {
        this.nomeCaixa = nomeCaixa;
    }
}
