#!/bin/zsh


echo ""
echo ""
echo ""
echo "#######################################################################################"
echo "#################################### run test #########################################"
echo "#######################################################################################"
echo ""
echo ""

#python -m pytest --cov=.

if [  "$?" = "0" ]; then
    echo ""
    echo ""
    echo "###################################################################################"
    echo "################################# git push ########################################"
    echo "###################################################################################"
    echo ""
    echo ""
    
    message="auto test2"
    #if [ $1 = '' ]; then
    #    message="auto commit and push"
    #fi 

    git add .
    git commit -m "$message"
    git push origin master

else
    echo ""
    echo ""
    echo "################################### test fail ####################################"
    echo ""
    echo ""

    exit 1
fi