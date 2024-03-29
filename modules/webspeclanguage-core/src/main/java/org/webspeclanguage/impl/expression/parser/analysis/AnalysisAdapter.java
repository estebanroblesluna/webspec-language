/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.webspeclanguage.impl.expression.parser.analysis;

import java.util.*;
import org.webspeclanguage.impl.expression.parser.node.*;

public class AnalysisAdapter implements Analysis
{
    private Hashtable<Node,Object> in;
    private Hashtable<Node,Object> out;

    public Object getIn(Node node)
    {
        if(this.in == null)
        {
            return null;
        }

        return this.in.get(node);
    }

    public void setIn(Node node, Object o)
    {
        if(this.in == null)
        {
            this.in = new Hashtable<Node,Object>(1);
        }

        if(o != null)
        {
            this.in.put(node, o);
        }
        else
        {
            this.in.remove(node);
        }
    }

    public Object getOut(Node node)
    {
        if(this.out == null)
        {
            return null;
        }

        return this.out.get(node);
    }

    public void setOut(Node node, Object o)
    {
        if(this.out == null)
        {
            this.out = new Hashtable<Node,Object>(1);
        }

        if(o != null)
        {
            this.out.put(node, o);
        }
        else
        {
            this.out.remove(node);
        }
    }

    public void caseStart(Start node)
    {
        defaultCase(node);
    }

    public void caseASingleactionActions(ASingleactionActions node)
    {
        defaultCase(node);
    }

    public void caseAManyactionsActions(AManyactionsActions node)
    {
        defaultCase(node);
    }

    public void caseALetAction(ALetAction node)
    {
        defaultCase(node);
    }

    public void caseAExprAction(AExprAction node)
    {
        defaultCase(node);
    }

    public void caseAOneargArguments(AOneargArguments node)
    {
        defaultCase(node);
    }

    public void caseAManyargsArguments(AManyargsArguments node)
    {
        defaultCase(node);
    }

    public void caseAAndExpr(AAndExpr node)
    {
        defaultCase(node);
    }

    public void caseAOrExpr(AOrExpr node)
    {
        defaultCase(node);
    }

    public void caseAImpliesExpr(AImpliesExpr node)
    {
        defaultCase(node);
    }

    public void caseANotExpr(ANotExpr node)
    {
        defaultCase(node);
    }

    public void caseACompExprExpr(ACompExprExpr node)
    {
        defaultCase(node);
    }

    public void caseAGreaterCompExpr(AGreaterCompExpr node)
    {
        defaultCase(node);
    }

    public void caseAGreaterEqualCompExpr(AGreaterEqualCompExpr node)
    {
        defaultCase(node);
    }

    public void caseANotEqualCompExpr(ANotEqualCompExpr node)
    {
        defaultCase(node);
    }

    public void caseAEqualCompExpr(AEqualCompExpr node)
    {
        defaultCase(node);
    }

    public void caseALowerCompExpr(ALowerCompExpr node)
    {
        defaultCase(node);
    }

    public void caseALowerEqualCompExpr(ALowerEqualCompExpr node)
    {
        defaultCase(node);
    }

    public void caseANumExprCompExpr(ANumExprCompExpr node)
    {
        defaultCase(node);
    }

    public void caseAAddNumExpr(AAddNumExpr node)
    {
        defaultCase(node);
    }

    public void caseASubNumExpr(ASubNumExpr node)
    {
        defaultCase(node);
    }

    public void caseAFactorNumExpr(AFactorNumExpr node)
    {
        defaultCase(node);
    }

    public void caseAMulFactor(AMulFactor node)
    {
        defaultCase(node);
    }

    public void caseADivFactor(ADivFactor node)
    {
        defaultCase(node);
    }

    public void caseAConcatFactor(AConcatFactor node)
    {
        defaultCase(node);
    }

    public void caseAValueFactor(AValueFactor node)
    {
        defaultCase(node);
    }

    public void caseANumberValue(ANumberValue node)
    {
        defaultCase(node);
    }

    public void caseAStringValue(AStringValue node)
    {
        defaultCase(node);
    }

    public void caseABooleanValue(ABooleanValue node)
    {
        defaultCase(node);
    }

    public void caseAFunctioncallValue(AFunctioncallValue node)
    {
        defaultCase(node);
    }

    public void caseAVariableValue(AVariableValue node)
    {
        defaultCase(node);
    }

    public void caseAGeneratorValue(AGeneratorValue node)
    {
        defaultCase(node);
    }

    public void caseAParensValue(AParensValue node)
    {
        defaultCase(node);
    }

    public void caseANativefunctioncallValue(ANativefunctioncallValue node)
    {
        defaultCase(node);
    }

    public void caseAArrayValue(AArrayValue node)
    {
        defaultCase(node);
    }

    public void caseAArrayAccessValue(AArrayAccessValue node)
    {
        defaultCase(node);
    }

    public void caseAWidgetPathValue(AWidgetPathValue node)
    {
        defaultCase(node);
    }

    public void caseAVariableVariableorliteralarray(AVariableVariableorliteralarray node)
    {
        defaultCase(node);
    }

    public void caseAArrayVariableorliteralarray(AArrayVariableorliteralarray node)
    {
        defaultCase(node);
    }

    public void caseAArray(AArray node)
    {
        defaultCase(node);
    }

    public void caseAVariable(AVariable node)
    {
        defaultCase(node);
    }

    public void caseAWidgetOrWidgetAccessListWithProperty(AWidgetOrWidgetAccessListWithProperty node)
    {
        defaultCase(node);
    }

    public void caseASimplewidgetWidgetOrWidgetAccess(ASimplewidgetWidgetOrWidgetAccess node)
    {
        defaultCase(node);
    }

    public void caseAWidgetarrayaccessWidgetOrWidgetAccess(AWidgetarrayaccessWidgetOrWidgetAccess node)
    {
        defaultCase(node);
    }

    public void caseATrueBoolean(ATrueBoolean node)
    {
        defaultCase(node);
    }

    public void caseAFalseBoolean(AFalseBoolean node)
    {
        defaultCase(node);
    }

    public void caseAStringTypeType(AStringTypeType node)
    {
        defaultCase(node);
    }

    public void caseANumberTypeType(ANumberTypeType node)
    {
        defaultCase(node);
    }

    public void caseABooleanTypeType(ABooleanTypeType node)
    {
        defaultCase(node);
    }

    public void caseTStringType(TStringType node)
    {
        defaultCase(node);
    }

    public void caseTNumberType(TNumberType node)
    {
        defaultCase(node);
    }

    public void caseTBooleanType(TBooleanType node)
    {
        defaultCase(node);
    }

    public void caseTAdd(TAdd node)
    {
        defaultCase(node);
    }

    public void caseTSub(TSub node)
    {
        defaultCase(node);
    }

    public void caseTMul(TMul node)
    {
        defaultCase(node);
    }

    public void caseTDiv(TDiv node)
    {
        defaultCase(node);
    }

    public void caseTVar(TVar node)
    {
        defaultCase(node);
    }

    public void caseTLeftBrace(TLeftBrace node)
    {
        defaultCase(node);
    }

    public void caseTRightBrace(TRightBrace node)
    {
        defaultCase(node);
    }

    public void caseTGreater(TGreater node)
    {
        defaultCase(node);
    }

    public void caseTGreaterEqual(TGreaterEqual node)
    {
        defaultCase(node);
    }

    public void caseTNotEqual(TNotEqual node)
    {
        defaultCase(node);
    }

    public void caseTEqual(TEqual node)
    {
        defaultCase(node);
    }

    public void caseTLower(TLower node)
    {
        defaultCase(node);
    }

    public void caseTLowerEqual(TLowerEqual node)
    {
        defaultCase(node);
    }

    public void caseTAnd(TAnd node)
    {
        defaultCase(node);
    }

    public void caseTImplies(TImplies node)
    {
        defaultCase(node);
    }

    public void caseTOr(TOr node)
    {
        defaultCase(node);
    }

    public void caseTNot(TNot node)
    {
        defaultCase(node);
    }

    public void caseTConcat(TConcat node)
    {
        defaultCase(node);
    }

    public void caseTLeftParen(TLeftParen node)
    {
        defaultCase(node);
    }

    public void caseTRightParen(TRightParen node)
    {
        defaultCase(node);
    }

    public void caseTNumber(TNumber node)
    {
        defaultCase(node);
    }

    public void caseTArrayIndex(TArrayIndex node)
    {
        defaultCase(node);
    }

    public void caseTTrue(TTrue node)
    {
        defaultCase(node);
    }

    public void caseTFalse(TFalse node)
    {
        defaultCase(node);
    }

    public void caseTWhitespaces(TWhitespaces node)
    {
        defaultCase(node);
    }

    public void caseTIdentifier(TIdentifier node)
    {
        defaultCase(node);
    }

    public void caseTString(TString node)
    {
        defaultCase(node);
    }

    public void caseTPoint(TPoint node)
    {
        defaultCase(node);
    }

    public void caseTSemicolon(TSemicolon node)
    {
        defaultCase(node);
    }

    public void caseTComma(TComma node)
    {
        defaultCase(node);
    }

    public void caseTAssign(TAssign node)
    {
        defaultCase(node);
    }

    public void caseTLeftBlock(TLeftBlock node)
    {
        defaultCase(node);
    }

    public void caseTRightBlock(TRightBlock node)
    {
        defaultCase(node);
    }

    public void caseTPercent(TPercent node)
    {
        defaultCase(node);
    }

    public void caseEOF(EOF node)
    {
        defaultCase(node);
    }

    public void defaultCase(@SuppressWarnings("unused") Node node)
    {
        // do nothing
    }
}
