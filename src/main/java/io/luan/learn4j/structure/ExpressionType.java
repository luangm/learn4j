package io.luan.learn4j.structure;

/**
 * @author Guangmiao Luan
 * @since 27/09/2017.
 */
public enum ExpressionType {
    Unknown,
    Absolute,
    Add,
    Assign,
    Constant,
    Divide,
    Fill,
    Group,
    Logarithm,
    MatMul,
    Multiply,
    Negate,
    Parameter,
    Power,
    ReduceMean,
    ReduceSum,
    Relu,
    Sigmoid,
    SigmoidGrad,
    Sign,
    Softmax,
    Square,
    Step,
    Subtract,
    Variable, Sine, Cosine, SquareRoot,
    Exponential, Tangent, Tanh, Conv2d,
    Reciprocal, SoftmaxGrad, TangentGrad,
    Tile, ReduceMin, ReduceMax, AddN, MultiAssign,
}
