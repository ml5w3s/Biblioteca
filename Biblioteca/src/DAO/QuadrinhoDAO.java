package DAO;
import DTO.CaixaDTO;
import DTO.QuadrinhoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class QuadrinhoDAO {    
    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    //ArrayList<PessoaDTO> list = new ArrayList<>();
    public void cadastrarQuadrinho(QuadrinhoDTO objquadrinhodto){
        String sql="INSERT INTO quadrinho (titulo, autor, id_caixa) VALUES(?,?,?)";
        conn = new ConexaoDAO().conectaBD();
        try{
            pstm=conn.prepareStatement(sql);
            pstm.setString(1,objquadrinhodto.getTitulo());
            pstm.setString(2,objquadrinhodto.getAutor());
            pstm.setInt(3,objquadrinhodto.getId_caixa());
            pstm.execute();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso!");
        }catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "FuncionarioDAO cadastrar "+erro);
        }
    }
            public boolean excluirQuadrinho(QuadrinhoDTO quadrinho) {
            conn = new ConexaoDAO().conectaBD();
            String sql="delete from quadrinho where id = ?;";

                try{
                    pstm = conn.prepareStatement(sql);
                    pstm.setInt(1, quadrinho.getId());
                    pstm.execute();
                    conn.close();
                    return true;

                }catch (SQLException ex){
                    System.out.println(ex);
                return false;

            }
        }
            public boolean alterarQuadrinho(QuadrinhoDTO quadrinho) {
            conn = new ConexaoDAO().conectaBD();
            String sql = "update quadrinho set titulo = ?, id_caixa = ?, autor = ?  where id = ?;";
            
                try  {
                    pstm = conn.prepareStatement(sql);
                    pstm.setString(1, quadrinho.getTitulo());
                    pstm.setString(2, quadrinho.getAutor());
                    pstm.setInt(3, quadrinho.getId_caixa());
                    pstm.setInt(4, quadrinho.getId());
                    pstm.execute();
                    conn.close();
                    
                    JOptionPane.showMessageDialog(null, "Quadrinho atualizado com sucesso!");
                }catch (SQLException ex){
                    JOptionPane.showMessageDialog(null, "FuncionarioDAO cadastrar "+ex);
        
                    return false;
                }
        
                    return true;
            }
        public ArrayList<QuadrinhoDTO>getListaQuadrinhosCaixa(){
        ArrayList<QuadrinhoDTO> lista = new ArrayList<>();
        conn = new ConexaoDAO().conectaBD();
         String sql="SELECT * FROM quadrinho left join caixa c on c.id = quadrinho.id_caixa;";
         try{
             pstm=conn.prepareStatement(sql);
             rs=pstm.executeQuery();
             QuadrinhoDTO quadrinho = null;
             while(rs.next()){                  
                 quadrinho = new QuadrinhoDTO();
                 quadrinho.setId(rs.getInt("id"));
                 quadrinho.setTitulo(rs.getString("titulo"));
                 quadrinho.setAutor(rs.getString("autor"));
                 quadrinho.setId_caixa(rs.getInt("id_caixa"));
                 lista.add(quadrinho);                 
              }
              pstm.close();
         }catch(SQLException ex){
             System.out.println(ex);
         }
         return lista;    
    }
 //SELECT quadrinho.*, c.nomeCaixa FROM quadrinho left join caixa c on c.id = quadrinho.id_caixa;   
    
}
