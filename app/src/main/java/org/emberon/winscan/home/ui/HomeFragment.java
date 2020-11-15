package org.emberon.winscan.home.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import org.emberon.winscan.base.BaseActivity;
import org.emberon.winscan.base.BaseFragment;
import org.emberon.winscan.databinding.FragmentHomeBinding;
import org.emberon.winscan.home.HomeContract;
import org.emberon.winscan.home.presenter.HomePresenter;
import org.emberon.winscan.util.Utils;

import javax.inject.Inject;

public class HomeFragment extends BaseFragment implements HomeContract.HomeView {

    @Inject
    HomePresenter presenter;
    private FragmentHomeBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseActivity) getActivity()).getActivityComponent().inject(this);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        presenter.attachView(this);

        binding.buttonPay.setOnClickListener(this::onPayButtonClicked);
        return binding.getRoot();
    }

    public void onPayButtonClicked(View view) {
        presenter.initiatePayment(
                binding.payeeName.getEditText().getText().toString(),
                binding.payeeUpiId.getEditText().getText().toString(),
                binding.payeeAmount.getEditText().getText().toString(),
                binding.payeeMerchantID.getEditText().getText().toString(),
                binding.payeeRemarks.getEditText().getText().toString());
    }

    @Override
    public void showToast(String message) {
        Utils.showToast(message);
    }
}
