grammar CREOLE;

options {language=Scala;output=AST;ASTLabelType=CHRTree;backtrack=true;}

@lexer::header {
package fr.emn.creole.parser;

import fr.emn.fullers.parser.tree._
}

@parser::header {
package fr.emn.creole.parser;

import fr.emn.fullers.parser.tree._
}

