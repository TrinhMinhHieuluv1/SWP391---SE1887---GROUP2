package dal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import model.LoanTerm;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoanTermDAO extends DBContext{
    
    public List<LoanTerm> selectAllLoanTerm(){
        List<LoanTerm> loanTermList = new ArrayList<>();
        String sql = "SELECT * FROM [LoanTerm]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                LoanTerm loanTermToAdd = new LoanTerm(rs.getInt("LoanTermID"), 
                                                    rs.getString("Type"), 
                                                    rs.getBigDecimal("MinAssetValue"), 
                                                    rs.getFloat("PercentOfAsset"), 
                                                    rs.getBigDecimal("MinSalary"),
                                                    rs.getFloat("NumberOfSalary"));
                loanTermList.add(loanTermToAdd);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return loanTermList;
    }
    
    public List<LoanTerm> selectLoanTermListByCondition(String Type){
        List<LoanTerm> loanTermList = new ArrayList<>();
        String sql = "SELECT * FROM [LoanTerm] WHERE (1=1)";
        if (Type != null && !Type.isEmpty()){
            sql = sql + " AND (Type='" + Type + "')";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                LoanTerm loanTermToAdd = new LoanTerm(rs.getInt("LoanTermID"), 
                                                    rs.getString("Type"), 
                                                    rs.getBigDecimal("MinAssetValue"), 
                                                    rs.getFloat("PercentOfAsset"), 
                                                    rs.getBigDecimal("MinSalary"),
                                                    rs.getFloat("NumberOfSalary"));
                loanTermList.add(loanTermToAdd);
            }
        } catch (SQLException e) {
        }
        return loanTermList;
    }
    
    public BigDecimal getMaxAmountForLoan(String Type, BigDecimal Asset, BigDecimal Salary){
        BigDecimal MaxAmount = BigDecimal.ZERO;
        if (Type.equals("Secured")){
            String sql = "SELECT PercentOfAsset FROM [LoanTerm] WHERE MinAssetValue=(SELECT MAX(MinAssetValue) FROM [LoanTerm] WHERE (MinAssetValue<=?))";
            try {
                PreparedStatement st = connection.prepareStatement(sql);
                st.setBigDecimal(1, Asset);
                ResultSet rs = st.executeQuery();
                while(rs.next()){
                    MaxAmount = Asset.multiply(new BigDecimal(rs.getFloat("PercentOfAsset")));
                }
            } catch (SQLException e) {
            }
        }
        else {
            String sql = "SELECT NumberOfSalary FROM [LoanTerm] WHERE MinSalary=(SELECT MAX(MinSalary) FROM LoanTerm WHERE (MinSalary<=?))";
            try {
                PreparedStatement st = connection.prepareStatement(sql);
                st.setBigDecimal(1, Salary);
                ResultSet rs = st.executeQuery();
                while(rs.next()){
                    MaxAmount = Salary.multiply(new BigDecimal(rs.getFloat("NumberOfSalary")));
                }
            } catch (SQLException e) {
            }
        }
        return MaxAmount;
    }

}
