/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.webspeclanguage.impl.expression.parser.analysis;

import java.util.*;
import org.webspeclanguage.impl.expression.parser.node.*;

public class ReversedDepthFirstAdapter extends AnalysisAdapter
{
    public void inStart(Start node)
    {
        defaultIn(node);
    }

    public void outStart(Start node)
    {
        defaultOut(node);
    }

    public void defaultIn(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    public void defaultOut(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    @Override
    public void caseStart(Start node)
    {
        inStart(node);
        node.getEOF().apply(this);
        node.getPActions().apply(this);
        outStart(node);
    }

    public void inASingleactionActions(ASingleactionActions node)
    {
        defaultIn(node);
    }

    public void outASingleactionActions(ASingleactionActions node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASingleactionActions(ASingleactionActions node)
    {
        inASingleactionActions(node);
        if(node.getAction() != null)
        {
            node.getAction().apply(this);
        }
        outASingleactionActions(node);
    }

    public void inAManyactionsActions(AManyactionsActions node)
    {
        defaultIn(node);
    }

    public void outAManyactionsActions(AManyactionsActions node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAManyactionsActions(AManyactionsActions node)
    {
        inAManyactionsActions(node);
        if(node.getActions() != null)
        {
            node.getActions().apply(this);
        }
        if(node.getSemicolon() != null)
        {
            node.getSemicolon().apply(this);
        }
        if(node.getAction() != null)
        {
            node.getAction().apply(this);
        }
        outAManyactionsActions(node);
    }

    public void inALetAction(ALetAction node)
    {
        defaultIn(node);
    }

    public void outALetAction(ALetAction node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALetAction(ALetAction node)
    {
        inALetAction(node);
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getAssign() != null)
        {
            node.getAssign().apply(this);
        }
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
        }
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
        outALetAction(node);
    }

    public void inAExprAction(AExprAction node)
    {
        defaultIn(node);
    }

    public void outAExprAction(AExprAction node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExprAction(AExprAction node)
    {
        inAExprAction(node);
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        outAExprAction(node);
    }

    public void inAOneargArguments(AOneargArguments node)
    {
        defaultIn(node);
    }

    public void outAOneargArguments(AOneargArguments node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAOneargArguments(AOneargArguments node)
    {
        inAOneargArguments(node);
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        outAOneargArguments(node);
    }

    public void inAManyargsArguments(AManyargsArguments node)
    {
        defaultIn(node);
    }

    public void outAManyargsArguments(AManyargsArguments node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAManyargsArguments(AManyargsArguments node)
    {
        inAManyargsArguments(node);
        if(node.getArguments() != null)
        {
            node.getArguments().apply(this);
        }
        if(node.getComma() != null)
        {
            node.getComma().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        outAManyargsArguments(node);
    }

    public void inAAndExpr(AAndExpr node)
    {
        defaultIn(node);
    }

    public void outAAndExpr(AAndExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAndExpr(AAndExpr node)
    {
        inAAndExpr(node);
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        if(node.getAnd() != null)
        {
            node.getAnd().apply(this);
        }
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        outAAndExpr(node);
    }

    public void inAOrExpr(AOrExpr node)
    {
        defaultIn(node);
    }

    public void outAOrExpr(AOrExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAOrExpr(AOrExpr node)
    {
        inAOrExpr(node);
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        if(node.getOr() != null)
        {
            node.getOr().apply(this);
        }
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        outAOrExpr(node);
    }

    public void inAImpliesExpr(AImpliesExpr node)
    {
        defaultIn(node);
    }

    public void outAImpliesExpr(AImpliesExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAImpliesExpr(AImpliesExpr node)
    {
        inAImpliesExpr(node);
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        if(node.getImplies() != null)
        {
            node.getImplies().apply(this);
        }
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        outAImpliesExpr(node);
    }

    public void inANotExpr(ANotExpr node)
    {
        defaultIn(node);
    }

    public void outANotExpr(ANotExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANotExpr(ANotExpr node)
    {
        inANotExpr(node);
        if(node.getCompExpr() != null)
        {
            node.getCompExpr().apply(this);
        }
        if(node.getNot() != null)
        {
            node.getNot().apply(this);
        }
        outANotExpr(node);
    }

    public void inACompExprExpr(ACompExprExpr node)
    {
        defaultIn(node);
    }

    public void outACompExprExpr(ACompExprExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseACompExprExpr(ACompExprExpr node)
    {
        inACompExprExpr(node);
        if(node.getCompExpr() != null)
        {
            node.getCompExpr().apply(this);
        }
        outACompExprExpr(node);
    }

    public void inAGreaterCompExpr(AGreaterCompExpr node)
    {
        defaultIn(node);
    }

    public void outAGreaterCompExpr(AGreaterCompExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAGreaterCompExpr(AGreaterCompExpr node)
    {
        inAGreaterCompExpr(node);
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        if(node.getGreater() != null)
        {
            node.getGreater().apply(this);
        }
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        outAGreaterCompExpr(node);
    }

    public void inAGreaterEqualCompExpr(AGreaterEqualCompExpr node)
    {
        defaultIn(node);
    }

    public void outAGreaterEqualCompExpr(AGreaterEqualCompExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAGreaterEqualCompExpr(AGreaterEqualCompExpr node)
    {
        inAGreaterEqualCompExpr(node);
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        if(node.getGreaterEqual() != null)
        {
            node.getGreaterEqual().apply(this);
        }
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        outAGreaterEqualCompExpr(node);
    }

    public void inANotEqualCompExpr(ANotEqualCompExpr node)
    {
        defaultIn(node);
    }

    public void outANotEqualCompExpr(ANotEqualCompExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANotEqualCompExpr(ANotEqualCompExpr node)
    {
        inANotEqualCompExpr(node);
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        if(node.getNotEqual() != null)
        {
            node.getNotEqual().apply(this);
        }
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        outANotEqualCompExpr(node);
    }

    public void inAEqualCompExpr(AEqualCompExpr node)
    {
        defaultIn(node);
    }

    public void outAEqualCompExpr(AEqualCompExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEqualCompExpr(AEqualCompExpr node)
    {
        inAEqualCompExpr(node);
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        if(node.getEqual() != null)
        {
            node.getEqual().apply(this);
        }
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        outAEqualCompExpr(node);
    }

    public void inALowerCompExpr(ALowerCompExpr node)
    {
        defaultIn(node);
    }

    public void outALowerCompExpr(ALowerCompExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALowerCompExpr(ALowerCompExpr node)
    {
        inALowerCompExpr(node);
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        if(node.getLower() != null)
        {
            node.getLower().apply(this);
        }
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        outALowerCompExpr(node);
    }

    public void inALowerEqualCompExpr(ALowerEqualCompExpr node)
    {
        defaultIn(node);
    }

    public void outALowerEqualCompExpr(ALowerEqualCompExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALowerEqualCompExpr(ALowerEqualCompExpr node)
    {
        inALowerEqualCompExpr(node);
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        if(node.getLowerEqual() != null)
        {
            node.getLowerEqual().apply(this);
        }
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        outALowerEqualCompExpr(node);
    }

    public void inANumExprCompExpr(ANumExprCompExpr node)
    {
        defaultIn(node);
    }

    public void outANumExprCompExpr(ANumExprCompExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANumExprCompExpr(ANumExprCompExpr node)
    {
        inANumExprCompExpr(node);
        if(node.getNumExpr() != null)
        {
            node.getNumExpr().apply(this);
        }
        outANumExprCompExpr(node);
    }

    public void inAAddNumExpr(AAddNumExpr node)
    {
        defaultIn(node);
    }

    public void outAAddNumExpr(AAddNumExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAddNumExpr(AAddNumExpr node)
    {
        inAAddNumExpr(node);
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        if(node.getAdd() != null)
        {
            node.getAdd().apply(this);
        }
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        outAAddNumExpr(node);
    }

    public void inASubNumExpr(ASubNumExpr node)
    {
        defaultIn(node);
    }

    public void outASubNumExpr(ASubNumExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASubNumExpr(ASubNumExpr node)
    {
        inASubNumExpr(node);
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        if(node.getSub() != null)
        {
            node.getSub().apply(this);
        }
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        outASubNumExpr(node);
    }

    public void inAFactorNumExpr(AFactorNumExpr node)
    {
        defaultIn(node);
    }

    public void outAFactorNumExpr(AFactorNumExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFactorNumExpr(AFactorNumExpr node)
    {
        inAFactorNumExpr(node);
        if(node.getFactor() != null)
        {
            node.getFactor().apply(this);
        }
        outAFactorNumExpr(node);
    }

    public void inAMulFactor(AMulFactor node)
    {
        defaultIn(node);
    }

    public void outAMulFactor(AMulFactor node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMulFactor(AMulFactor node)
    {
        inAMulFactor(node);
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        if(node.getMul() != null)
        {
            node.getMul().apply(this);
        }
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        outAMulFactor(node);
    }

    public void inADivFactor(ADivFactor node)
    {
        defaultIn(node);
    }

    public void outADivFactor(ADivFactor node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADivFactor(ADivFactor node)
    {
        inADivFactor(node);
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        if(node.getDiv() != null)
        {
            node.getDiv().apply(this);
        }
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        outADivFactor(node);
    }

    public void inAConcatFactor(AConcatFactor node)
    {
        defaultIn(node);
    }

    public void outAConcatFactor(AConcatFactor node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAConcatFactor(AConcatFactor node)
    {
        inAConcatFactor(node);
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        if(node.getConcat() != null)
        {
            node.getConcat().apply(this);
        }
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        outAConcatFactor(node);
    }

    public void inAValueFactor(AValueFactor node)
    {
        defaultIn(node);
    }

    public void outAValueFactor(AValueFactor node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAValueFactor(AValueFactor node)
    {
        inAValueFactor(node);
        if(node.getValue() != null)
        {
            node.getValue().apply(this);
        }
        outAValueFactor(node);
    }

    public void inANumberValue(ANumberValue node)
    {
        defaultIn(node);
    }

    public void outANumberValue(ANumberValue node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANumberValue(ANumberValue node)
    {
        inANumberValue(node);
        if(node.getNumber() != null)
        {
            node.getNumber().apply(this);
        }
        outANumberValue(node);
    }

    public void inAStringValue(AStringValue node)
    {
        defaultIn(node);
    }

    public void outAStringValue(AStringValue node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAStringValue(AStringValue node)
    {
        inAStringValue(node);
        if(node.getString() != null)
        {
            node.getString().apply(this);
        }
        outAStringValue(node);
    }

    public void inABooleanValue(ABooleanValue node)
    {
        defaultIn(node);
    }

    public void outABooleanValue(ABooleanValue node)
    {
        defaultOut(node);
    }

    @Override
    public void caseABooleanValue(ABooleanValue node)
    {
        inABooleanValue(node);
        if(node.getBoolean() != null)
        {
            node.getBoolean().apply(this);
        }
        outABooleanValue(node);
    }

    public void inAFunctioncallValue(AFunctioncallValue node)
    {
        defaultIn(node);
    }

    public void outAFunctioncallValue(AFunctioncallValue node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFunctioncallValue(AFunctioncallValue node)
    {
        inAFunctioncallValue(node);
        if(node.getRightParen() != null)
        {
            node.getRightParen().apply(this);
        }
        if(node.getArguments() != null)
        {
            node.getArguments().apply(this);
        }
        if(node.getLeftParen() != null)
        {
            node.getLeftParen().apply(this);
        }
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
        }
        outAFunctioncallValue(node);
    }

    public void inAVariableValue(AVariableValue node)
    {
        defaultIn(node);
    }

    public void outAVariableValue(AVariableValue node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVariableValue(AVariableValue node)
    {
        inAVariableValue(node);
        if(node.getVariable() != null)
        {
            node.getVariable().apply(this);
        }
        outAVariableValue(node);
    }

    public void inAGeneratorValue(AGeneratorValue node)
    {
        defaultIn(node);
    }

    public void outAGeneratorValue(AGeneratorValue node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAGeneratorValue(AGeneratorValue node)
    {
        inAGeneratorValue(node);
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
        }
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        outAGeneratorValue(node);
    }

    public void inAParensValue(AParensValue node)
    {
        defaultIn(node);
    }

    public void outAParensValue(AParensValue node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAParensValue(AParensValue node)
    {
        inAParensValue(node);
        if(node.getRightParen() != null)
        {
            node.getRightParen().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getLeftParen() != null)
        {
            node.getLeftParen().apply(this);
        }
        outAParensValue(node);
    }

    public void inANativefunctioncallValue(ANativefunctioncallValue node)
    {
        defaultIn(node);
    }

    public void outANativefunctioncallValue(ANativefunctioncallValue node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANativefunctioncallValue(ANativefunctioncallValue node)
    {
        inANativefunctioncallValue(node);
        if(node.getRightParen() != null)
        {
            node.getRightParen().apply(this);
        }
        if(node.getArguments() != null)
        {
            node.getArguments().apply(this);
        }
        if(node.getLeftParen() != null)
        {
            node.getLeftParen().apply(this);
        }
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
        }
        if(node.getPercent() != null)
        {
            node.getPercent().apply(this);
        }
        outANativefunctioncallValue(node);
    }

    public void inAArrayValue(AArrayValue node)
    {
        defaultIn(node);
    }

    public void outAArrayValue(AArrayValue node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAArrayValue(AArrayValue node)
    {
        inAArrayValue(node);
        if(node.getArray() != null)
        {
            node.getArray().apply(this);
        }
        outAArrayValue(node);
    }

    public void inAArrayAccessValue(AArrayAccessValue node)
    {
        defaultIn(node);
    }

    public void outAArrayAccessValue(AArrayAccessValue node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAArrayAccessValue(AArrayAccessValue node)
    {
        inAArrayAccessValue(node);
        if(node.getRightBlock() != null)
        {
            node.getRightBlock().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getLeftBlock() != null)
        {
            node.getLeftBlock().apply(this);
        }
        if(node.getVariableorliteralarray() != null)
        {
            node.getVariableorliteralarray().apply(this);
        }
        outAArrayAccessValue(node);
    }

    public void inAWidgetPathValue(AWidgetPathValue node)
    {
        defaultIn(node);
    }

    public void outAWidgetPathValue(AWidgetPathValue node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAWidgetPathValue(AWidgetPathValue node)
    {
        inAWidgetPathValue(node);
        {
            List<PWidgetOrWidgetAccessListWithProperty> copy = new ArrayList<PWidgetOrWidgetAccessListWithProperty>(node.getWidgets());
            Collections.reverse(copy);
            for(PWidgetOrWidgetAccessListWithProperty e : copy)
            {
                e.apply(this);
            }
        }
        if(node.getInteraction() != null)
        {
            node.getInteraction().apply(this);
        }
        outAWidgetPathValue(node);
    }

    public void inAVariableVariableorliteralarray(AVariableVariableorliteralarray node)
    {
        defaultIn(node);
    }

    public void outAVariableVariableorliteralarray(AVariableVariableorliteralarray node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVariableVariableorliteralarray(AVariableVariableorliteralarray node)
    {
        inAVariableVariableorliteralarray(node);
        if(node.getVariable() != null)
        {
            node.getVariable().apply(this);
        }
        outAVariableVariableorliteralarray(node);
    }

    public void inAArrayVariableorliteralarray(AArrayVariableorliteralarray node)
    {
        defaultIn(node);
    }

    public void outAArrayVariableorliteralarray(AArrayVariableorliteralarray node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAArrayVariableorliteralarray(AArrayVariableorliteralarray node)
    {
        inAArrayVariableorliteralarray(node);
        if(node.getArray() != null)
        {
            node.getArray().apply(this);
        }
        outAArrayVariableorliteralarray(node);
    }

    public void inAArray(AArray node)
    {
        defaultIn(node);
    }

    public void outAArray(AArray node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAArray(AArray node)
    {
        inAArray(node);
        if(node.getRightBlock() != null)
        {
            node.getRightBlock().apply(this);
        }
        if(node.getArguments() != null)
        {
            node.getArguments().apply(this);
        }
        if(node.getLeftBlock() != null)
        {
            node.getLeftBlock().apply(this);
        }
        outAArray(node);
    }

    public void inAVariable(AVariable node)
    {
        defaultIn(node);
    }

    public void outAVariable(AVariable node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVariable(AVariable node)
    {
        inAVariable(node);
        if(node.getRightBrace() != null)
        {
            node.getRightBrace().apply(this);
        }
        if(node.getI() != null)
        {
            node.getI().apply(this);
        }
        if(node.getLeftBrace() != null)
        {
            node.getLeftBrace().apply(this);
        }
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        outAVariable(node);
    }

    public void inAWidgetOrWidgetAccessListWithProperty(AWidgetOrWidgetAccessListWithProperty node)
    {
        defaultIn(node);
    }

    public void outAWidgetOrWidgetAccessListWithProperty(AWidgetOrWidgetAccessListWithProperty node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAWidgetOrWidgetAccessListWithProperty(AWidgetOrWidgetAccessListWithProperty node)
    {
        inAWidgetOrWidgetAccessListWithProperty(node);
        if(node.getWidgetOrWidgetAccess() != null)
        {
            node.getWidgetOrWidgetAccess().apply(this);
        }
        if(node.getP() != null)
        {
            node.getP().apply(this);
        }
        outAWidgetOrWidgetAccessListWithProperty(node);
    }

    public void inASimplewidgetWidgetOrWidgetAccess(ASimplewidgetWidgetOrWidgetAccess node)
    {
        defaultIn(node);
    }

    public void outASimplewidgetWidgetOrWidgetAccess(ASimplewidgetWidgetOrWidgetAccess node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASimplewidgetWidgetOrWidgetAccess(ASimplewidgetWidgetOrWidgetAccess node)
    {
        inASimplewidgetWidgetOrWidgetAccess(node);
        if(node.getWidget() != null)
        {
            node.getWidget().apply(this);
        }
        outASimplewidgetWidgetOrWidgetAccess(node);
    }

    public void inAWidgetarrayaccessWidgetOrWidgetAccess(AWidgetarrayaccessWidgetOrWidgetAccess node)
    {
        defaultIn(node);
    }

    public void outAWidgetarrayaccessWidgetOrWidgetAccess(AWidgetarrayaccessWidgetOrWidgetAccess node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAWidgetarrayaccessWidgetOrWidgetAccess(AWidgetarrayaccessWidgetOrWidgetAccess node)
    {
        inAWidgetarrayaccessWidgetOrWidgetAccess(node);
        if(node.getRightBlock() != null)
        {
            node.getRightBlock().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getLeftBlock() != null)
        {
            node.getLeftBlock().apply(this);
        }
        if(node.getWidget() != null)
        {
            node.getWidget().apply(this);
        }
        outAWidgetarrayaccessWidgetOrWidgetAccess(node);
    }

    public void inATrueBoolean(ATrueBoolean node)
    {
        defaultIn(node);
    }

    public void outATrueBoolean(ATrueBoolean node)
    {
        defaultOut(node);
    }

    @Override
    public void caseATrueBoolean(ATrueBoolean node)
    {
        inATrueBoolean(node);
        if(node.getTrue() != null)
        {
            node.getTrue().apply(this);
        }
        outATrueBoolean(node);
    }

    public void inAFalseBoolean(AFalseBoolean node)
    {
        defaultIn(node);
    }

    public void outAFalseBoolean(AFalseBoolean node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFalseBoolean(AFalseBoolean node)
    {
        inAFalseBoolean(node);
        if(node.getFalse() != null)
        {
            node.getFalse().apply(this);
        }
        outAFalseBoolean(node);
    }

    public void inAStringTypeType(AStringTypeType node)
    {
        defaultIn(node);
    }

    public void outAStringTypeType(AStringTypeType node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAStringTypeType(AStringTypeType node)
    {
        inAStringTypeType(node);
        if(node.getStringType() != null)
        {
            node.getStringType().apply(this);
        }
        outAStringTypeType(node);
    }

    public void inANumberTypeType(ANumberTypeType node)
    {
        defaultIn(node);
    }

    public void outANumberTypeType(ANumberTypeType node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANumberTypeType(ANumberTypeType node)
    {
        inANumberTypeType(node);
        if(node.getNumberType() != null)
        {
            node.getNumberType().apply(this);
        }
        outANumberTypeType(node);
    }

    public void inABooleanTypeType(ABooleanTypeType node)
    {
        defaultIn(node);
    }

    public void outABooleanTypeType(ABooleanTypeType node)
    {
        defaultOut(node);
    }

    @Override
    public void caseABooleanTypeType(ABooleanTypeType node)
    {
        inABooleanTypeType(node);
        if(node.getBooleanType() != null)
        {
            node.getBooleanType().apply(this);
        }
        outABooleanTypeType(node);
    }
}
