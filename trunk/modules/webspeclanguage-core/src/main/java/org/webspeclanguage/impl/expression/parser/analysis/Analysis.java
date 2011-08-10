/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.webspeclanguage.impl.expression.parser.analysis;

import org.webspeclanguage.impl.expression.parser.node.*;

public interface Analysis extends Switch
{
    Object getIn(Node node);
    void setIn(Node node, Object o);
    Object getOut(Node node);
    void setOut(Node node, Object o);

    void caseStart(Start node);
    void caseASingleactionActions(ASingleactionActions node);
    void caseAManyactionsActions(AManyactionsActions node);
    void caseALetAction(ALetAction node);
    void caseAExprAction(AExprAction node);
    void caseAOneargArguments(AOneargArguments node);
    void caseAManyargsArguments(AManyargsArguments node);
    void caseAAndExpr(AAndExpr node);
    void caseAOrExpr(AOrExpr node);
    void caseAImpliesExpr(AImpliesExpr node);
    void caseANotExpr(ANotExpr node);
    void caseACompExprExpr(ACompExprExpr node);
    void caseAGreaterCompExpr(AGreaterCompExpr node);
    void caseAGreaterEqualCompExpr(AGreaterEqualCompExpr node);
    void caseANotEqualCompExpr(ANotEqualCompExpr node);
    void caseAEqualCompExpr(AEqualCompExpr node);
    void caseALowerCompExpr(ALowerCompExpr node);
    void caseALowerEqualCompExpr(ALowerEqualCompExpr node);
    void caseANumExprCompExpr(ANumExprCompExpr node);
    void caseAAddNumExpr(AAddNumExpr node);
    void caseASubNumExpr(ASubNumExpr node);
    void caseAFactorNumExpr(AFactorNumExpr node);
    void caseAMulFactor(AMulFactor node);
    void caseADivFactor(ADivFactor node);
    void caseAConcatFactor(AConcatFactor node);
    void caseAValueFactor(AValueFactor node);
    void caseANumberValue(ANumberValue node);
    void caseAStringValue(AStringValue node);
    void caseABooleanValue(ABooleanValue node);
    void caseAFunctioncallValue(AFunctioncallValue node);
    void caseAVariableValue(AVariableValue node);
    void caseAGeneratorValue(AGeneratorValue node);
    void caseAParensValue(AParensValue node);
    void caseANativefunctioncallValue(ANativefunctioncallValue node);
    void caseAArrayValue(AArrayValue node);
    void caseAArrayAccessValue(AArrayAccessValue node);
    void caseAWidgetPathValue(AWidgetPathValue node);
    void caseAVariableVariableorliteralarray(AVariableVariableorliteralarray node);
    void caseAArrayVariableorliteralarray(AArrayVariableorliteralarray node);
    void caseAArray(AArray node);
    void caseAVariable(AVariable node);
    void caseAWidgetOrWidgetAccessListWithProperty(AWidgetOrWidgetAccessListWithProperty node);
    void caseASimplewidgetWidgetOrWidgetAccess(ASimplewidgetWidgetOrWidgetAccess node);
    void caseAWidgetarrayaccessWidgetOrWidgetAccess(AWidgetarrayaccessWidgetOrWidgetAccess node);
    void caseATrueBoolean(ATrueBoolean node);
    void caseAFalseBoolean(AFalseBoolean node);
    void caseAStringTypeType(AStringTypeType node);
    void caseANumberTypeType(ANumberTypeType node);
    void caseABooleanTypeType(ABooleanTypeType node);

    void caseTStringType(TStringType node);
    void caseTNumberType(TNumberType node);
    void caseTBooleanType(TBooleanType node);
    void caseTAdd(TAdd node);
    void caseTSub(TSub node);
    void caseTMul(TMul node);
    void caseTDiv(TDiv node);
    void caseTVar(TVar node);
    void caseTLeftBrace(TLeftBrace node);
    void caseTRightBrace(TRightBrace node);
    void caseTGreater(TGreater node);
    void caseTGreaterEqual(TGreaterEqual node);
    void caseTNotEqual(TNotEqual node);
    void caseTEqual(TEqual node);
    void caseTLower(TLower node);
    void caseTLowerEqual(TLowerEqual node);
    void caseTAnd(TAnd node);
    void caseTImplies(TImplies node);
    void caseTOr(TOr node);
    void caseTNot(TNot node);
    void caseTConcat(TConcat node);
    void caseTLeftParen(TLeftParen node);
    void caseTRightParen(TRightParen node);
    void caseTNumber(TNumber node);
    void caseTArrayIndex(TArrayIndex node);
    void caseTTrue(TTrue node);
    void caseTFalse(TFalse node);
    void caseTWhitespaces(TWhitespaces node);
    void caseTIdentifier(TIdentifier node);
    void caseTString(TString node);
    void caseTPoint(TPoint node);
    void caseTSemicolon(TSemicolon node);
    void caseTComma(TComma node);
    void caseTAssign(TAssign node);
    void caseTLeftBlock(TLeftBlock node);
    void caseTRightBlock(TRightBlock node);
    void caseTPercent(TPercent node);
    void caseEOF(EOF node);
}