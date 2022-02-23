//package com.jzysoft.web.controller.system.cMcurr;
//
//import com.gxhscloud.mes.oracle.entity.tree.BaseTree;
//import com.gxhscloud.mes.oracle.entity.tree.SbomTree;
//import com.gxhscloud.mes.oracle.entity.tree.TreeNode;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang.StringUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by Ace on 2017/6/12.
// */
//@Slf4j
//public class TreeUtil {
//  /**
//   * 两层循环实现建树
//   *
//   * @param treeNodes 传入的树节点列表
//   * @return
//   */
//  public static <T extends TreeNode> List<T> bulid(List<T> treeNodes, Object root) {
//
//    List<T> trees = new ArrayList<T>();
//
//    for (T treeNode : treeNodes) {
//
//      if (root.equals(treeNode.getParentId())) {
//        trees.add(treeNode);
//      }
//
//      for (T it : treeNodes) {
//        if (it.getParentId() == treeNode.getId()) {
//          if (treeNode.getChildren() == null) {
//            treeNode.setChildren(new ArrayList<TreeNode>());
//          }
//          treeNode.add(it);
//        }
//      }
//    }
//    return trees;
//  }
//  /**
//   * 两层循环实现建树
//   *
//   * @param treeNodes 传入的树节点列表
//   * @return
//   */
//  public static <T extends BaseTree> List<T> bulidTree(List<T> treeNodes, Object root,String projektNr) {
//    return bulidTree(treeNodes,root,false ,projektNr);
//  }
//
//  /**
//   * 两层循环实现建树
//   *
//   * @param treeNodes 传入的树节点列表
//   * @return
//   */
//  public static <T extends BaseTree> List<T> bulidTree(List<T> treeNodes, Object root,boolean relevance,String projektNr) {
//
//    List<T> trees = new ArrayList<>();
//
//      boolean isRoot = true;
//      if (null != root && !"-1".equals(root.toString())) {
//          isRoot = false;
//      }
//      boolean eixt=false;
//      for (T treeNode : treeNodes) {
//          if (!isRoot) {
//              if(treeNode.getId().equals(root.toString())) {
//                  trees.add(treeNode);
//                  eixt = true;
//                  if (relevance) {
//                      if (null != treeNode.getAttributes() && "0".equals(treeNode.getAttributes().get("down"))) {
//                          continue;
//                      }
//                  }
//              }else{
//                  continue;
//              }
//          } else {
//              if (relevance) {
//                  if (null != treeNode.getAttributes() && "0".equals(treeNode.getAttributes().get("open"))) {
//                      continue;
//                  }
//              }
//                //需要传个项目号
//              if (relevance ||  null == root || treeNode.getParentId().equals(projektNr)) {
//                  trees.add(treeNode);
//              }
//
//              if (relevance) {
//                  if (null != treeNode.getAttributes() && "0".equals(treeNode.getAttributes().get("down"))) {
//                      continue;
//                  }
//              }
//          }
//          for (T it : treeNodes) {
//              if (it.getParentId().equals(treeNode.getId())) {
//                  if (relevance) {
//                      if (null != it.getAttributes() && "0".equals(it.getAttributes().get("open"))) {
//                          continue;
//                      }
//                  }
//                  if (treeNode.getNodes() == null) {
//                      treeNode.setNodes(new ArrayList<>());
//                      treeNode.setChildren(new ArrayList<>());
//
//                  }
//                  System.out.println("父："+treeNode.getId()+"，子："+it.getId());
//                  treeNode.getNodes().add(it);
//                  treeNode.getChildren().add(it);
//              }
//          }
//          if(eixt) {
//              break;
//          }
//      }
//    return trees;
//  }
//
//  /**
//   * 使用递归方法建树
//   *
//   * @param treeNodes
//   * @return
//   */
//  public static <T extends TreeNode> List<T> buildByRecursive(List<T> treeNodes,Object root) {
//    List<T> trees = new ArrayList<T>();
//    for (T treeNode : treeNodes) {
//      if (root.equals(treeNode.getParentId())) {
//        trees.add(findChildren(treeNode, treeNodes));
//      }
//    }
//    return trees;
//  }
//
//  /**
//   * 递归查找子节点
//   *
//   * @param treeNodes
//   * @return
//   */
//  public static <T extends TreeNode> T findChildren(T treeNode, List<T> treeNodes) {
//    for (T it : treeNodes) {
//      if (treeNode.getId() == it.getParentId()) {
//        if (treeNode.getChildren() == null) {
//          treeNode.setChildren(new ArrayList<TreeNode>());
//        }
//        treeNode.add(findChildren(it, treeNodes));
//      }
//    }
//    return treeNode;
//  }
//
//    public static <T extends BaseTree> List<T> bulidTree2(List<T> treeNodes, Object root,String projektNr) {
//        return bulidTree2(treeNodes,root,false ,projektNr);
//    }
//    /**
//     * 两层循环实现建树
//     *
//     * @param treeNodes 传入的树节点列表
//     * @return
//     */
//    public static <T extends BaseTree> List<T> bulidTree2(List<T> treeNodes, Object root,boolean relevance,String projektNr) {
//
//        List<T> trees = new ArrayList<>();
//
//        boolean isRoot = true;
//        if (null != root && !"-1".equals(root.toString())) {
//            isRoot = false;
//        }
//        boolean eixt=false;
//        for (T treeNode : treeNodes) {
//            if (!isRoot) {
//                if(treeNode.getId().equals(root.toString())) {
//                    trees.add(treeNode);
//                    eixt = true;
//                    if (relevance) {
//                        if (null != treeNode.getAttributes() && "0".equals(treeNode.getAttributes().get("down"))) {
//                            continue;
//                        }
//                    }
//                }else{
//                    continue;
//                }
//            } else {
//                if (relevance) {
//                    if (null != treeNode.getAttributes() && "0".equals(treeNode.getAttributes().get("open"))) {
//                        continue;
//                    }
//                }
//                //需要传个项目号
//                if (relevance ||  null == root || treeNode.getParentId().equals(projektNr)) {
//                    trees.add(treeNode);
//                }
//
//                if (relevance) {
//                    if (null != treeNode.getAttributes() && "0".equals(treeNode.getAttributes().get("down"))) {
//                        continue;
//                    }
//                }
//            }
//            for (T it : treeNodes) {
//                if (it.getParentId().equals(treeNode.getId())) {
//                    if (relevance) {
//                        if (null != it.getAttributes() && "0".equals(it.getAttributes().get("open"))) {
//                            continue;
//                        }
//                    }
//                    if (treeNode.getNodes() == null) {
//                        treeNode.setNodes(new ArrayList<>());
//                        treeNode.setChildren(new ArrayList<>());
//
//                    }
//                    System.out.println("父："+treeNode.getId()+"，子："+it.getId());
//                    treeNode.getNodes().add(it);
//                    treeNode.getChildren().add(it);
////                    if (StringUtils.isNotEmpty(it.getGrandparentId())){
////                        if (it.getGrandparentId().equals(treeNode.getParentId())){
////                            System.out.println("爷："+it.getGrandparentId()+"父："+treeNode.getId()+"，子："+it.getId());
////                            treeNode.getNodes().add(it);
////                            treeNode.getChildren().add(it);
////                        }
////                    }else {
////                        System.out.println("父："+treeNode.getId()+"，子："+it.getId());
////                        treeNode.getNodes().add(it);
////                        treeNode.getChildren().add(it);
////                    }
//
//                }
//            }
//            if(eixt) {
//                break;
//            }
//        }
//        return trees;
//    }
//
//    public static <T extends SbomTree> List<T> bulidTree3(List<T> treeNodes, Object root, String projektNr) {
//        return bulidTree3(treeNodes,root,false ,projektNr);
//    }
//
//    /**
//     * 两层循环实现建树
//     *
//     * @param treeNodes 传入的树节点列表
//     * @return
//     */
//    public static <T extends SbomTree> List<T> bulidTree3(List<T> treeNodes, Object root,boolean relevance,String projektNr) {
//
//        List<T> trees = new ArrayList<>();
//
//        boolean isRoot = true;
//        if (null != root && !"-1".equals(root.toString())) {
//            isRoot = false;
//        }
//        boolean eixt=false;
//        for (T treeNode : treeNodes) {
//            if (!isRoot) {
//                if(treeNode.getId().equals(root.toString())) {
//                    trees.add(treeNode);
//                    eixt = true;
//
//                }else{
//                    continue;
//                }
//            } else {
//
//                //需要传个项目号
//                if (relevance ||  null == root || treeNode.getParentId().equals(projektNr)) {
//                    trees.add(treeNode);
//                }
//
//
//            }
//            for (T it : treeNodes) {
//                if (it.getParentId().equals(treeNode.getId())) {
//
//                    System.out.println("父："+treeNode.getId()+"，子："+it.getId());
//                    treeNode.getChildren().add(it);
////                    if (StringUtils.isNotEmpty(it.getGrandparentId())){
////                        if (it.getGrandparentId().equals(treeNode.getParentId())){
////                            System.out.println("爷："+it.getGrandparentId()+"父："+treeNode.getId()+"，子："+it.getId());
////                            treeNode.getNodes().add(it);
////                            treeNode.getChildren().add(it);
////                        }
////                    }else {
////                        System.out.println("父："+treeNode.getId()+"，子："+it.getId());
////                        treeNode.getNodes().add(it);
////                        treeNode.getChildren().add(it);
////                    }
//
//                }
//            }
//            if(eixt) {
//                break;
//            }
//        }
//        return trees;
//    }
//}
