package DAO;
import DTO.CaixaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

public class CaixaDAO {
 Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    //ArrayList<PessoaDTO> list = new ArrayList<>();
    public void cadastrarCaixa(CaixaDTO objcaixadto){
        String sql="INSERT INTO caixa (nomeCaixa) VALUES(?)";
        conn = new ConexaoDAO().conectaBD();
        try{
            pstm=conn.prepareStatement(sql);
            pstm.setString(1,objcaixadto.getNomeCaixa());
            pstm.execute();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso!");
        }catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "FuncionarioDAO cadastrar "+erro);
        }
    }
    
        public ArrayList<CaixaDTO>getListaCaixa(){
        ArrayList<CaixaDTO> lista = new ArrayList<>();
        conn = new ConexaoDAO().conectaBD();
         String sql="select * from caixa;";
         try{
             pstm=conn.prepareStatement(sql);
             rs=pstm.executeQuery();
             CaixaDTO caixa = null;
             while(rs.next()){
                 caixa = new CaixaDTO();
                 caixa.setId(rs.getInt("id"));
                 caixa.setNomeCaixa(rs.getString("nomeCaixa"));
                 lista.add(caixa);
              }
              pstm.close();
         }catch(SQLException ex){
             System.out.println(ex);
         }
         return lista;    
    }
            public boolean alterarCaixa(CaixaDTO caixa) {
            conn = new ConexaoDAO().conectaBD();
            String sql = "update caixa set nomeCaixa = ? where id = ?;";
            
                try  {
                    pstm = conn.prepareStatement(sql);
                    pstm.setString(1, caixa.getNomeCaixa());
                    pstm.setInt(2, caixa.getId());
                    pstm.execute();
                    conn.close();
                    
                    JOptionPane.showMessageDialog(null, "Nome atualizado com sucesso!");
                }catch (SQLException ex){
                    JOptionPane.showMessageDialog(null, "FuncionarioDAO cadastrar "+ex);
        
                    return false;
                }
        
                    return true;
            }
            
            public HashMap<String, Integer> populateCombo(){
              HashMap<String, Integer> map = new HashMap<String, Integer>();
              conn = new ConexaoDAO().conectaBD();            
         try{
             
             pstm=conn.prepareStatement("select * from caixa;");
             rs=pstm.executeQuery();
             CaixaDTO caixa;
             while(rs.next()){
                 caixa = new CaixaDTO(rs.getInt(1),rs.getString(2));
                 map.put(caixa.getNomeCaixa(), caixa.getId());
              }
              pstm.close();
         }catch(SQLException ex){
             System.out.println(ex);
         }
         return map;    
              
            }
 
}        
